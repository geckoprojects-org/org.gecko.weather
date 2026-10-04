/*
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
 *      Data In Motion - initial API and implementation
 */
package org.gecko.weather.outlook.model.outlook.configuration;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.fennec.emf.osgi.configurator.EPackageConfigurator;

import org.eclipse.fennec.emf.osgi.constants.EMFNamespaces;

import org.gecko.weather.outlook.model.outlook.OutlookPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>EPackageConfiguration</b> and <b>ResourceFactoryConfigurator</b> for the model.
 * The package will be registered into a OSGi base model registry.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * What a weather page shows for a site: the next hours one by one and the next days in summary. A presentation built by a consumer of the weather service, not part of it — every hour carries one value per quantity, picked from the report's per-source datasets by a fixed, documented rule, and the outlook names the sources it picked from. Plain data types only (EDate, EDouble, EInt, EString) so that a TypeScript client reads it without the java.time conversion delegate.
 * <!-- end-model-doc -->
 * @see EPackageConfigurator
 * @generated
 */
public class OutlookEPackageConfigurator implements EPackageConfigurator {
	
	/**
	 * The fingerprint of this model version, computed from the <code>.ecore</code> at build
	 * time. It identifies the model content, not the artifact - see the <code>emf.fingerprint</code>
	 * service property.
	 * @generated
	 */
	public static final String FINGERPRINT = "fp1:e8884841780624de5dee64196b38a6e0c19ffb72c463ffc03836fccc41153efc";

	private OutlookPackage ePackage;

	protected OutlookEPackageConfigurator(OutlookPackage ePackage){
		this.ePackage = ePackage;
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.fennec.emf.osgi.EPackageRegistryConfigurator#configureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void configureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.put(OutlookPackage.eNS_URI, ePackage);
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.fennec.emf.osgi.EPackageRegistryConfigurator#unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry)
	 * @generated
	 */
	@Override
	public void unconfigureEPackage(org.eclipse.emf.ecore.EPackage.Registry registry) {
		registry.remove(OutlookPackage.eNS_URI);
	}
	
	/**
	 * A method providing the Properties the services around this Model should be registered with.
	 * @generated
	 */
	public Map<String, Object> getServiceProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EMFNamespaces.EMF_NAME, OutlookPackage.eNAME);
		properties.put(EMFNamespaces.EMF_MODEL_NSURI, OutlookPackage.eNS_URI);
		properties.put(EMFNamespaces.EMF_MODEL_REGISTRATION, EMFNamespaces.MODEL_REGISTRATION_PROVIDED);
		properties.put(EMFNamespaces.EMF_MODEL_FILE_EXT, "outlook");
		properties.put(EMFNamespaces.EMF_MODEL_VERSION, "1.0");
		properties.put(EMFNamespaces.EMF_MODEL_FINGERPRINT, FINGERPRINT);
		return properties;
	}
}