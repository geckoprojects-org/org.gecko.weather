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

import org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate;
import org.eclipse.fennec.emf.osgi.constants.EMFNamespaces;
import org.osgi.service.component.annotations.Component;

/**
 * Publishes the {@link JavaTimeConversionDelegateFactory} to the Fennec conversion delegate
 * whiteboard. The {@code DefaultConversionDelegateRegistry} component of
 * {@code org.eclipse.fennec.emf.osgi} picks it up by {@code emf.configuratorType} and puts it into
 * EMF's global registry under {@code emf.configuratorName}.
 *
 * @author Mark Hoffmann
 * @since 03.10.2026
 */
@Component(service = ConversionDelegate.Factory.class, property = {
		EMFNamespaces.EMF_CONFIGURATOR_TYPE + "=CONVERSION_DELEGATE_FACTORY",
		EMFNamespaces.EMF_CONFIGURATOR_NAME + "=" + JavaTimeConversionDelegateFactory.DELEGATE_URI })
public class JavaTimeConversionDelegateComponent extends JavaTimeConversionDelegateFactory {

}
