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
package org.gecko.weather.pv.internal;

/**
 * The physics of one array in one hour, as plain functions: how much of the sky's light reaches a
 * tilted plane, how warm the cells get, what that leaves of the rated power. Standard models, each
 * named where it is used, so that a number can be traced:
 * <ul>
 * <li>decomposition of global into direct and diffuse where a source does not split it — Erbs,
 * Klein, Duffie (1982);</li>
 * <li>transposition onto the plane — beam by the angle of incidence, sky diffuse by Hay and Davies
 * (1980), ground reflection isotropic;</li>
 * <li>cell temperature — Faiman (2008), with heat-loss coefficients per mounting;</li>
 * <li>DC power — linear in irradiance with the temperature coefficient of Pmax.</li>
 * </ul>
 * Angles in degrees, irradiance in W/m², azimuth clockwise from north.
 *
 * @author Mark Hoffmann
 * @since 04.10.2026
 */
public final class PvPhysics {

	/** Solar constant, W/m² */
	static final double SOLAR_CONSTANT = 1361.0;
	/** Below this elevation the direct normal irradiance is not divided out — it would explode. */
	static final double MIN_ELEVATION_FOR_BEAM = 2.0;

	private PvPhysics() {
	}

	/** Irradiance at the top of the atmosphere, normal to the sun, for a day of the year. */
	public static double extraterrestrial(int dayOfYear) {
		return SOLAR_CONSTANT * (1 + 0.033 * Math.cos(2 * Math.PI * dayOfYear / 365.0));
	}

	/**
	 * Erbs: the diffuse fraction of global radiation from the clearness index kt = G / (I0 · sin e).
	 *
	 * @return diffuse horizontal irradiance
	 */
	public static double erbsDiffuse(double global, double elevation, double extraterrestrial) {
		if (global <= 0) {
			return 0;
		}
		double sinE = Math.sin(Math.toRadians(Math.max(elevation, MIN_ELEVATION_FOR_BEAM)));
		double kt = Math.min(1.0, global / (extraterrestrial * sinE));
		double kd;
		if (kt <= 0.22) {
			kd = 1 - 0.09 * kt;
		} else if (kt <= 0.80) {
			kd = 0.9511 - 0.1604 * kt + 4.388 * kt * kt - 16.638 * kt * kt * kt + 12.336 * kt * kt * kt * kt;
		} else {
			kd = 0.165;
		}
		return global * kd;
	}

	/** Cosine of the angle between the sun and the plane's normal; negative when the sun is behind it. */
	public static double cosIncidence(double sunElevation, double sunAzimuth, double tilt, double planeAzimuth) {
		double z = Math.toRadians(90 - sunElevation);
		double b = Math.toRadians(tilt);
		return Math.cos(z) * Math.cos(b) + Math.sin(z) * Math.sin(b) * Math.cos(Math.toRadians(sunAzimuth - planeAzimuth));
	}

	/** What reaches the plane, split by where it comes from. */
	public record PlaneIrradiance(double beam, double skyDiffuse, double ground) {
		public double total() {
			return beam + skyDiffuse + ground;
		}
	}

	/**
	 * Transposition onto a tilted plane.
	 *
	 * @param directHorizontal  beam on the horizontal, W/m²
	 * @param diffuseHorizontal diffuse on the horizontal, W/m²
	 * @param sunBlocked        the sun is behind the horizon line for this plane: no beam, no
	 *                          circumsolar part
	 * @param skyViewLoss       share of the isotropic sky the horizon hides, 0..1
	 */
	public static PlaneIrradiance plane(double directHorizontal, double diffuseHorizontal, double sunElevation, double sunAzimuth,
			double tilt, double planeAzimuth, double albedo, double extraterrestrial, boolean sunBlocked, double skyViewLoss) {
		double global = directHorizontal + diffuseHorizontal;
		double ground = global * albedo * (1 - Math.cos(Math.toRadians(tilt))) / 2;
		double isotropicView = (1 + Math.cos(Math.toRadians(tilt))) / 2 * (1 - skyViewLoss);
		if (sunElevation <= 0 || global <= 0) {
			// sun under the horizon in the middle of the hour, light from twilight: all of it diffuse
			return new PlaneIrradiance(0, Math.max(0, global) * isotropicView, Math.max(0, ground));
		}
		double sinE = Math.sin(Math.toRadians(Math.max(sunElevation, MIN_ELEVATION_FOR_BEAM)));
		double dni = Math.min(directHorizontal / sinE, extraterrestrial);
		double cosTheta = cosIncidence(sunElevation, sunAzimuth, tilt, planeAzimuth);
		double anisotropy = Math.max(0, Math.min(1, dni / extraterrestrial));
		double rb = Math.max(0, cosTheta) / Math.max(sinE, Math.cos(Math.toRadians(85)));
		double beam = sunBlocked ? 0 : dni * Math.max(0, cosTheta);
		double circumsolar = sunBlocked ? 0 : diffuseHorizontal * anisotropy * rb;
		double isotropic = diffuseHorizontal * (1 - anisotropy) * isotropicView;
		return new PlaneIrradiance(beam, circumsolar + isotropic, ground);
	}

	/** Faiman heat-loss coefficients U0 (W/m²K) and U1 (W/m³sK) per mounting. */
	public record HeatLoss(double u0, double u1) {
	}

	/**
	 * Faiman: cell temperature from air temperature, plane irradiance and wind.
	 *
	 * @param windSpeed m/s at 10 m; taken down to module height by a factor of 0.75
	 */
	public static double cellTemperature(double airTemperature, double planeIrradiance, double windSpeed, HeatLoss loss) {
		double wind = Math.max(0, windSpeed) * 0.75;
		return airTemperature + planeIrradiance / (loss.u0() + loss.u1() * wind);
	}

	/**
	 * DC power of an array, kW.
	 *
	 * @param temperatureCoefficient %/K, negative
	 * @param systemLosses           % not modelled otherwise
	 */
	public static double dcPower(double peakPower, double planeIrradiance, double cellTemperature, double temperatureCoefficient,
			double systemLosses) {
		if (planeIrradiance <= 0) {
			return 0;
		}
		double temperature = 1 + temperatureCoefficient / 100.0 * (cellTemperature - 25);
		return Math.max(0, peakPower * planeIrradiance / 1000.0 * temperature * (1 - systemLosses / 100.0));
	}

	/** AC power of an inverter, kW, and whether its ceiling cut it. */
	public record AcPower(double power, boolean clipped) {
	}

	public static AcPower acPower(double dcPower, double efficiency, double acLimit) {
		double ac = dcPower * efficiency;
		if (acLimit > 0 && ac > acLimit) {
			return new AcPower(acLimit, true);
		}
		return new AcPower(ac, false);
	}
}
