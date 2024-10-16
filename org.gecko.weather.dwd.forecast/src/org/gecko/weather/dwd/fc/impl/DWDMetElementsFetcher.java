/**
 * Copyright (c) 2012 - 2024 Data In Motion and others.
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
package org.gecko.weather.dwd.fc.impl;

import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.weather.api.fetcher.DWDEMFFetcher;
import org.gecko.weather.api.util.DWDUtils;
import org.osgi.service.component.annotations.Reference;

import biz.aQute.scheduler.api.Constants;
import biz.aQute.scheduler.api.CronExpression;
import biz.aQute.scheduler.api.CronJob;
import de.dwd.cdc.metelements.MetElementDefinitionType;
import de.dwd.cdc.metelements.MetElementType;
import de.dwd.cdc.metelements.MetElementsPackage;

/**
 * Fetches the MetElementDefinitions
 * 
 * @author mark
 * @since 05.09.2024
 */
@CronExpression(name = "Meterological-Definitions", cron = Constants.CRON_EXPRESSION_REBOOT)
//@Component(immediate = true)
public class DWDMetElementsFetcher extends DWDEMFFetcher<MetElementDefinitionType> implements CronJob {
	private static final Logger LOGGER = System.getLogger(DWDMetElementsFetcher.class.getName());

	private static final String MET_URL = "https://opendata.dwd.de/weather/lib/MetElementDefinition.xml";
	@Reference
	private MetElementsPackage metDefPackage;
	@Reference
	private ResourceSet resourceSet;

	@Override
	public void run() throws Exception {
		InputStream data = doDownload();
		MetElementDefinitionType defType = doLoad(data);
		for (MetElementType type : defType.getMetElement()) {
			LOGGER.log(Level.INFO, "Name: {0}\nDescription: {1}\nUnit: {2}", type.getShortName(), type.getDescription(),
					type.getUnitOfMeasurement());
		}
	}

	@Override
	protected String getModelFileExtension() {
		return "metelements";
	}

	@Override
	protected String getFetchUrl() {
		return MET_URL;
	}

	@Override
	protected ResourceSet getResourceSet() {
		return resourceSet;
	}

	@Override
	protected String getName() {
		return "Met-Definition";
	}
	
	@Override
	protected MetElementDefinitionType get(EObject content) {
		return DWDUtils.getMetDefinitions(content);
	}

}
