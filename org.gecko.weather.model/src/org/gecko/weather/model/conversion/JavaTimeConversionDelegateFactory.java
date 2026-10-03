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
package org.gecko.weather.model.conversion;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate;

/**
 * Conversion delegate factory for the {@code java.time} data types of the weather model.
 * <p>
 * EMF cannot serialise {@link Instant}, {@link Duration} or {@link LocalDate} on its own: none of
 * them has a {@code String} constructor or a static {@code valueOf(String)}. The model therefore
 * declares the Ecore annotation {@code conversionDelegates="java.time"} and marks each of these
 * data types with an annotation of source {@value #DELEGATE_URI}; EMF then asks the factory
 * registered under that URI for a delegate.
 * <p>
 * Inside OSGi the factory is registered by {@link JavaTimeConversionDelegateComponent}. Outside
 * OSGi — in plain JUnit tests, tools — call {@link #register()} once before the first
 * serialisation. EMF caches a missing delegate per data type, so registering after the first
 * save or load has no effect.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
public class JavaTimeConversionDelegateFactory implements ConversionDelegate.Factory {

	/** The delegate URI the model refers to, see the {@code conversionDelegates} annotation in {@code weather.ecore}. */
	public static final String DELEGATE_URI = "java.time";

	private static final Map<String, ConversionDelegate> DELEGATES = Map.of(
			Instant.class.getName(), new Iso8601Delegate<>(Instant.class, Instant::parse),
			Duration.class.getName(), new Iso8601Delegate<>(Duration.class, Duration::parse),
			LocalDate.class.getName(), new Iso8601Delegate<>(LocalDate.class, LocalDate::parse));

	/**
	 * Registers this factory in EMF's global {@link ConversionDelegate.Factory.Registry} for use
	 * without OSGi. Idempotent.
	 */
	public static void register() {
		ConversionDelegate.Factory.Registry.INSTANCE.put(DELEGATE_URI, new JavaTimeConversionDelegateFactory());
	}

	@Override
	public ConversionDelegate createConversionDelegate(EDataType eDataType) {
		return DELEGATES.get(eDataType.getInstanceClassName());
	}

	/**
	 * Round-trips a {@code java.time} type through its ISO-8601 {@code toString()} / {@code parse()} pair.
	 */
	private static final class Iso8601Delegate<T> implements ConversionDelegate {

		private final Class<T> type;
		private final Function<String, T> parser;

		Iso8601Delegate(Class<T> type, Function<String, T> parser) {
			this.type = type;
			this.parser = parser;
		}

		@Override
		public String convertToString(Object value) {
			if (value == null) {
				return null;
			}
			if (!type.isInstance(value)) {
				throw new IllegalArgumentException(
						"Expected " + type.getName() + " but got " + value.getClass().getName());
			}
			return value.toString();
		}

		@Override
		public Object createFromString(String literal) {
			if (literal == null || literal.isBlank()) {
				return null;
			}
			return parser.apply(literal.strip());
		}
	}

}
