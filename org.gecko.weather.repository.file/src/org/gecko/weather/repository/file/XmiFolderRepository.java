/**
 * Copyright (c) 2012 - 2026 Data In Motion and others.
 * All rights reserved.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package org.gecko.weather.repository.file;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.gecko.weather.api.repository.RepositoryException;
import org.gecko.weather.api.repository.WeatherRepository;
import org.gecko.weather.model.conversion.JavaTimeConversionDelegateFactory;
import org.gecko.weather.model.weather.Site;
import org.gecko.weather.model.weather.SourceDataset;
import org.gecko.weather.model.weather.SourceStateRecord;
import org.gecko.weather.model.weather.StationCatalog;
import org.gecko.weather.model.weather.WeatherPackage;
import org.gecko.weather.model.weather.WeatherReport;

/**
 * {@link WeatherRepository} as a folder of XMI files — the plain-Java core, usable without OSGi.
 *
 * <pre>
 * root/
 *   sites/&lt;siteId&gt;.xmi.gz
 *   reports/&lt;siteId&gt;.xmi.gz
 *   archive/&lt;siteId&gt;/&lt;providerId&gt;/&lt;productId&gt;/&lt;yyyyMMddTHHmmssZ&gt;.xmi.gz   one per superseded issue
 *   catalogs/&lt;providerId&gt;/&lt;productId&gt;.xmi.gz
 *   state/&lt;providerId&gt;/&lt;productId&gt;.xmi.gz    SourceStateRecord — the ingest runtime's validators per URL
 *
 * Files are gzip-compressed XMI by default (a report with provenance per value shrinks ~50x); plain
 * .xmi files from before are read as well and replaced by their compressed form on the next save.
 * </pre>
 *
 * Identifiers are percent-encoded into file names ({@link FileNames}), so any id works. Writes go to
 * a sibling temp file and are moved into place atomically, so a crash never leaves a half-written
 * file where a good one was. Writes to one site are serialised; different sites do not block each
 * other. Every operation uses a fresh {@link ResourceSet}, which is what makes loaded objects
 * detached copies.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class XmiFolderRepository implements WeatherRepository {

	private static final String SITES = "sites";
	private static final String REPORTS = "reports";
	private static final String ARCHIVE = "archive";
	private static final String CATALOGS = "catalogs";
	private static final String STATE = "state";

	private static final Map<Object, Object> SAVE_OPTIONS = Map.of(XMLResource.OPTION_ENCODING, "UTF-8",
			XMLResource.OPTION_FORMATTED, Boolean.TRUE);

	private final Path root;
	private final Supplier<ResourceSet> resourceSets;
	private final boolean compress;
	private final ConcurrentHashMap<String, Object> siteLocks = new ConcurrentHashMap<>();

	/** A compressing repository over the folder with plain EMF resource sets — for tests and tools. */
	public XmiFolderRepository(Path root) {
		this(root, ResourceSetImpl::new);
	}

	public XmiFolderRepository(Path root, Supplier<ResourceSet> resourceSets) {
		this(root, resourceSets, true);
	}

	/**
	 * @param root         the folder; created if missing
	 * @param resourceSets supplies the resource set for an operation — a fresh one each time, or the
	 *                     same one every time (in OSGi: the prototype-scoped {@code ResourceSet} service
	 *                     targeted at the weather model). Operations synchronise on the resource set
	 *                     and remove their resource from it afterwards, so sharing one is safe.
	 * @param compress     write {@code .xmi.gz}; false writes plain {@code .xmi}. Reading takes both.
	 */
	public XmiFolderRepository(Path root, Supplier<ResourceSet> resourceSets, boolean compress) {
		this.root = requireNonNull(root, "root").toAbsolutePath().normalize();
		this.resourceSets = requireNonNull(resourceSets, "resourceSets");
		this.compress = compress;
		// EMF caches a missing delegate per data type, so this has to precede the first XMI operation.
		JavaTimeConversionDelegateFactory.register();
		try {
			for (String dir : List.of(SITES, REPORTS, ARCHIVE, CATALOGS, STATE)) {
				Files.createDirectories(this.root.resolve(dir));
			}
		} catch (IOException e) {
			throw new RepositoryException("Cannot create repository folders under " + this.root, e);
		}
	}

	public Path getRoot() {
		return root;
	}

	// --- sites ---------------------------------------------------------------------------

	@Override
	public Optional<Site> loadSite(String siteId) {
		return load(siteFile(siteId), Site.class);
	}

	@Override
	public List<Site> loadSites() {
		List<Site> sites = new ArrayList<>();
		try (DirectoryStream<Path> files = Files.newDirectoryStream(root.resolve(SITES), XmiFolderRepository::isData)) {
			for (Path file : files) {
				load(file, Site.class).ifPresent(sites::add);
			}
		} catch (IOException e) {
			throw new RepositoryException("Cannot list sites in " + root.resolve(SITES), e);
		}
		sites.sort(Comparator.comparing(Site::getId));
		return sites;
	}

	@Override
	public void saveSite(Site site) {
		requireNonNull(site, "site");
		requireId(site.getId(), "site");
		synchronized (lock(site.getId())) {
			save(siteFile(site.getId()), site);
		}
	}

	@Override
	public void deleteSite(String siteId) {
		requireId(siteId, "site");
		synchronized (lock(siteId)) {
			try {
				deleteVariants(siteFile(siteId));
				deleteVariants(reportFile(siteId));
				deleteTree(archiveDir(siteId));
			} catch (IOException e) {
				throw new RepositoryException("Cannot delete site " + siteId, e);
			}
		}
	}

	// --- reports -------------------------------------------------------------------------

	@Override
	public Optional<WeatherReport> loadReport(String siteId) {
		return load(reportFile(siteId), WeatherReport.class);
	}

	@Override
	public void saveReport(WeatherReport report) {
		requireNonNull(report, "report");
		requireId(report.getSiteId(), "report.siteId");
		synchronized (lock(report.getSiteId())) {
			save(reportFile(report.getSiteId()), report);
		}
	}

	// --- archive -------------------------------------------------------------------------

	@Override
	public void archive(String siteId, SourceDataset dataset) {
		requireId(siteId, "site");
		requireNonNull(dataset, "dataset");
		requireId(dataset.getProviderId(), "dataset.providerId");
		requireId(dataset.getProductId(), "dataset.productId");
		requireNonNull(dataset.getIssuedAt(), "dataset.issuedAt");
		synchronized (lock(siteId)) {
			Path dir = archiveDir(siteId, dataset.getProviderId(), dataset.getProductId());
			String stamp = FileNames.stamp(dataset.getIssuedAt());
			Path file = dir.resolve(stamp + extension());
			// append-only: a second dataset with the same issue second gets a suffix, never a replace
			for (int n = 1; existing(file).isPresent(); n++) {
				file = dir.resolve(stamp + "-" + n + extension());
			}
			save(file, dataset);
		}
	}

	@Override
	public List<SourceDataset> loadArchive(String siteId, String providerId, String productId, Instant issuedFrom,
			Instant issuedTo) {
		requireNonNull(issuedFrom, "issuedFrom");
		requireNonNull(issuedTo, "issuedTo");
		Path dir = archiveDir(siteId, providerId, productId);
		if (!Files.isDirectory(dir)) {
			return List.of();
		}
		List<Path> files = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, XmiFolderRepository::isData)) {
			for (Path file : stream) {
				FileNames.stampOf(file.getFileName().toString())
						.filter(issued -> !issued.isBefore(issuedFrom) && issued.isBefore(issuedTo))
						.ifPresent(issued -> files.add(file));
			}
		} catch (IOException e) {
			throw new RepositoryException("Cannot list archive " + dir, e);
		}
		// the stamp sorts as text; newest first, and the "-n" suffix after its base
		files.sort(Comparator.comparing((Path p) -> p.getFileName().toString()).reversed());
		List<SourceDataset> result = new ArrayList<>(files.size());
		for (Path file : files) {
			load(file, SourceDataset.class).ifPresent(result::add);
		}
		return result;
	}

	@Override
	public int evictArchive(String siteId, Instant issuedBefore) {
		requireId(siteId, "site");
		requireNonNull(issuedBefore, "issuedBefore");
		Path dir = archiveDir(siteId);
		if (!Files.isDirectory(dir)) {
			return 0;
		}
		synchronized (lock(siteId)) {
			int removed = 0;
			try (Stream<Path> files = Files.walk(dir)) {
				for (Path file : files.filter(Files::isRegularFile).toList()) {
					Optional<Instant> issued = FileNames.stampOf(file.getFileName().toString());
					if (issued.isPresent() && issued.get().isBefore(issuedBefore)) {
						Files.delete(file);
						removed++;
					}
				}
			} catch (IOException e) {
				throw new RepositoryException("Cannot evict archive of site " + siteId, e);
			}
			return removed;
		}
	}

	// --- catalogues ----------------------------------------------------------------------

	@Override
	public Optional<StationCatalog> loadCatalog(String providerId, String productId) {
		return load(catalogFile(providerId, productId), StationCatalog.class);
	}

	@Override
	public void saveCatalog(StationCatalog catalog) {
		requireNonNull(catalog, "catalog");
		requireId(catalog.getProviderId(), "catalog.providerId");
		requireId(catalog.getProductId(), "catalog.productId");
		synchronized (lock("catalog:" + catalog.getProviderId() + "/" + catalog.getProductId())) {
			save(catalogFile(catalog.getProviderId(), catalog.getProductId()), catalog);
		}
	}

	// --- ingest state --------------------------------------------------------------------

	@Override
	public Optional<SourceStateRecord> loadSourceState(String providerId, String productId) {
		return load(stateFile(providerId, productId), SourceStateRecord.class);
	}

	@Override
	public void saveSourceState(SourceStateRecord state) {
		requireNonNull(state, "state");
		requireId(state.getProviderId(), "state.providerId");
		requireId(state.getProductId(), "state.productId");
		synchronized (lock("state:" + state.getProviderId() + "/" + state.getProductId())) {
			save(stateFile(state.getProviderId(), state.getProductId()), state);
		}
	}

	// --- files ---------------------------------------------------------------------------

	private Path siteFile(String siteId) {
		requireId(siteId, "site");
		return root.resolve(SITES).resolve(FileNames.of(siteId) + extension());
	}

	private Path reportFile(String siteId) {
		requireId(siteId, "site");
		return root.resolve(REPORTS).resolve(FileNames.of(siteId) + extension());
	}

	private Path archiveDir(String siteId) {
		requireId(siteId, "site");
		return root.resolve(ARCHIVE).resolve(FileNames.of(siteId));
	}

	private Path archiveDir(String siteId, String providerId, String productId) {
		requireId(providerId, "providerId");
		requireId(productId, "productId");
		return archiveDir(siteId).resolve(FileNames.of(providerId)).resolve(FileNames.of(productId));
	}

	private Path stateFile(String providerId, String productId) {
		requireId(providerId, "providerId");
		requireId(productId, "productId");
		return root.resolve(STATE).resolve(FileNames.of(providerId)).resolve(FileNames.of(productId) + extension());
	}

	private Path catalogFile(String providerId, String productId) {
		requireId(providerId, "providerId");
		requireId(productId, "productId");
		return root.resolve(CATALOGS).resolve(FileNames.of(providerId)).resolve(FileNames.of(productId) + extension());
	}

	// --- XMI -----------------------------------------------------------------------------

	private String extension() {
		return FileNames.extension(compress);
	}

	private static boolean isData(Path file) {
		return FileNames.isData(file.getFileName().toString()) && Files.isRegularFile(file);
	}

	/** The compressed or plain sibling of the given file name, whichever exists — compressed first. */
	private static Optional<Path> existing(Path file) {
		String base = FileNames.stripExtension(file.getFileName().toString());
		for (String ext : List.of(FileNames.XMI_GZ, FileNames.XMI)) {
			Path candidate = file.resolveSibling(base + ext);
			if (Files.isRegularFile(candidate)) {
				return Optional.of(candidate);
			}
		}
		return Optional.empty();
	}

	private static void deleteVariants(Path file) throws IOException {
		String base = FileNames.stripExtension(file.getFileName().toString());
		Files.deleteIfExists(file.resolveSibling(base + FileNames.XMI_GZ));
		Files.deleteIfExists(file.resolveSibling(base + FileNames.XMI));
	}

	/** The EMF resource URI: always the plain .xmi name, so the XMI factory applies whatever the file is called. */
	private static URI resourceUri(Path file) {
		String base = FileNames.stripExtension(file.getFileName().toString());
		return URI.createFileURI(file.resolveSibling(base + FileNames.XMI).toString());
	}

	private <T extends EObject> Optional<T> load(Path wanted, Class<T> type) {
		Path file = existing(wanted).orElse(null);
		if (file == null) {
			return Optional.empty();
		}
		ResourceSet rs = resourceSet();
		synchronized (rs) {
			Resource resource = rs.createResource(resourceUri(file));
			try (InputStream raw = Files.newInputStream(file);
					InputStream in = FileNames.isCompressed(file.getFileName().toString()) ? new GZIPInputStream(raw) : raw) {
				resource.load(in, null);
				if (!resource.getErrors().isEmpty()) {
					throw new RepositoryException(
							"Corrupt content in " + file + ": " + resource.getErrors().get(0).getMessage());
				}
				if (resource.getContents().size() != 1 || !type.isInstance(resource.getContents().get(0))) {
					throw new RepositoryException("Expected one " + type.getSimpleName() + " in " + file);
				}
				T root = type.cast(resource.getContents().get(0));
				resource.getContents().clear(); // detach: the caller gets an object without a resource
				return Optional.of(root);
			} catch (IOException e) {
				throw new RepositoryException("Cannot read " + file, e);
			} finally {
				rs.getResources().remove(resource);
			}
		}
	}

	/** Writes the file (compressed or not, by its name) atomically and removes the other variant. */
	private void save(Path file, EObject root) {
		Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
		ResourceSet rs = resourceSet();
		synchronized (rs) {
			Resource resource = rs.createResource(resourceUri(file));
			try {
				Files.createDirectories(file.getParent());
				// a copy, so that the caller's object is neither moved into our resource nor changed
				resource.getContents().add(EcoreUtil.copy(root));
				try (OutputStream raw = Files.newOutputStream(tmp);
						OutputStream out = FileNames.isCompressed(file.getFileName().toString()) ? new GZIPOutputStream(raw) : raw) {
					resource.save(out, SAVE_OPTIONS);
				}
				move(tmp, file);
				String base = FileNames.stripExtension(file.getFileName().toString());
				String other = FileNames.isCompressed(file.getFileName().toString()) ? FileNames.XMI : FileNames.XMI_GZ;
				Files.deleteIfExists(file.resolveSibling(base + other));
			} catch (IOException e) {
				try {
					Files.deleteIfExists(tmp);
				} catch (IOException ignored) {
					// the original error is the one to report
				}
				throw new RepositoryException("Cannot write " + file, e);
			} finally {
				rs.getResources().remove(resource);
			}
		}
	}

	private static void move(Path from, Path to) throws IOException {
		try {
			Files.move(from, to, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
		} catch (AtomicMoveNotSupportedException e) {
			Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	private ResourceSet resourceSet() {
		ResourceSet rs = resourceSets.get();
		// a plain ResourceSetImpl knows neither; a Fennec one already does
		rs.getPackageRegistry().putIfAbsent(WeatherPackage.eNS_URI, WeatherPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().putIfAbsent("xmi", new XMIResourceFactoryImpl());
		return rs;
	}

	private static void deleteTree(Path dir) throws IOException {
		if (!Files.exists(dir)) {
			return;
		}
		try (Stream<Path> walk = Files.walk(dir)) {
			for (Path p : walk.sorted(Comparator.reverseOrder()).toList()) {
				try {
					Files.delete(p);
				} catch (NoSuchFileException ignored) {
					// already gone
				}
			}
		}
	}

	private Object lock(String key) {
		return siteLocks.computeIfAbsent(key, k -> new Object());
	}

	private static void requireId(String id, String what) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException(what + " id is required");
		}
	}

}
