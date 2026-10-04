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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.time.LocalDate;

import org.gecko.weather.pv.internal.PvPhysics.HeatLoss;
import org.gecko.weather.pv.internal.PvPhysics.PlaneIrradiance;
import org.gecko.weather.pv.model.pv.Mounting;
import org.gecko.weather.pv.model.pv.Obstacle;
import org.gecko.weather.pv.model.pv.Plant;
import org.gecko.weather.pv.model.pv.PvFactory;
import org.junit.jupiter.api.Test;

/** The physics against hand-computed values and invariants (QR-6: a known expected value per formula). */
class PvPhysicsTest {

	private static final double I0 = PvPhysics.extraterrestrial(172);

	@Test
	void extraterrestrialVariesOverTheYear() {
		assertThat(PvPhysics.extraterrestrial(1)).isCloseTo(1361 * (1 + 0.033 * Math.cos(2 * Math.PI / 365)), within(1e-9));
		assertThat(PvPhysics.extraterrestrial(1)).isGreaterThan(PvPhysics.extraterrestrial(182));
	}

	@Test
	void aPlaneFacingTheSunSeesItHeadOn() {
		// sun at 30° elevation in the south, plane tilted 60° to the south: normal points straight at the sun
		assertThat(PvPhysics.cosIncidence(30, 180, 60, 180)).isCloseTo(1.0, within(1e-12));
		// a vertical north facade with the sun in the south: behind it
		assertThat(PvPhysics.cosIncidence(30, 180, 90, 0)).isLessThan(0);
		// a flat plane: cos θ = sin e
		assertThat(PvPhysics.cosIncidence(30, 123, 0, 180)).isCloseTo(0.5, within(1e-12));
	}

	@Test
	void aHorizontalPlaneReceivesExactlyTheGlobalRadiation() {
		PlaneIrradiance p = PvPhysics.plane(400, 150, 35, 170, 0, 180, 0.2, I0, 1, 0);
		assertThat(p.total()).isCloseTo(550, within(1e-9));
		assertThat(p.beam()).isCloseTo(400, within(1e-9));
		assertThat(p.ground()).isZero();
	}

	@Test
	void transpositionOfASouthRoof() {
		// 35° south roof, sun 40° high due south: the beam meets it at 15°
		PlaneIrradiance p = PvPhysics.plane(500, 100, 40, 180, 35, 180, 0.2, I0, 1, 0);
		double dni = 500 / Math.sin(Math.toRadians(40));
		assertThat(p.beam()).isCloseTo(dni * Math.cos(Math.toRadians(15)), within(1e-6));
		double ai = dni / I0;
		double rb = Math.cos(Math.toRadians(15)) / Math.sin(Math.toRadians(40));
		assertThat(p.skyDiffuse()).isCloseTo(100 * (ai * rb + (1 - ai) * (1 + Math.cos(Math.toRadians(35))) / 2), within(1e-6));
		assertThat(p.ground()).isCloseTo(600 * 0.2 * (1 - Math.cos(Math.toRadians(35))) / 2, within(1e-9));
		assertThat(p.total()).isGreaterThan(600); // the tilt gains on a sunny day
	}

	@Test
	void aBlockedSunLeavesOnlyTheSkyAndTheGround() {
		PlaneIrradiance open = PvPhysics.plane(500, 100, 20, 180, 35, 180, 0.2, I0, 1, 0);
		PlaneIrradiance blocked = PvPhysics.plane(500, 100, 20, 180, 35, 180, 0.2, I0, 0, 0);
		assertThat(blocked.beam()).isZero();
		assertThat(blocked.skyDiffuse()).isLessThan(open.skyDiffuse()); // the circumsolar part is gone too
		assertThat(blocked.ground()).isEqualTo(open.ground());
		// the sun below the horizon in the middle of the hour: all of it isotropic diffuse
		PlaneIrradiance twilight = PvPhysics.plane(5, 20, -1, 270, 0, 180, 0.2, I0, 1, 0);
		assertThat(twilight.beam()).isZero();
		assertThat(twilight.total()).isCloseTo(25, within(1e-9));
	}

	@Test
	void erbs() {
		double sin30 = 0.5;
		// kt = 0.1: kd = 1 - 0.009
		double g = 0.1 * I0 * sin30;
		assertThat(PvPhysics.erbsDiffuse(g, 30, I0)).isCloseTo(g * 0.991, within(1e-9));
		// kt = 0.5: the polynomial
		g = 0.5 * I0 * sin30;
		double kd = 0.9511 - 0.1604 * 0.5 + 4.388 * 0.25 - 16.638 * 0.125 + 12.336 * 0.0625;
		assertThat(PvPhysics.erbsDiffuse(g, 30, I0)).isCloseTo(g * kd, within(1e-9));
		// a clear sky, kt 0.85: a sixth diffuse
		g = 0.85 * I0 * sin30;
		assertThat(PvPhysics.erbsDiffuse(g, 30, I0)).isCloseTo(g * 0.165, within(1e-9));
		assertThat(PvPhysics.erbsDiffuse(0, 30, I0)).isZero();
	}

	@Test
	void cellTemperatureDcAndAc() {
		HeatLoss roof = PlantForecaster.heatLoss(Mounting.ROOF_MOUNTED);
		assertThat(PvPhysics.cellTemperature(25, 800, 1, roof)).isCloseTo(25 + 800 / (20 + 4.5 * 0.75), within(1e-9));
		assertThat(PvPhysics.cellTemperature(10, 0, 3, roof)).isEqualTo(10);
		assertThat(PlantForecaster.heatLoss(Mounting.ROOF_INTEGRATED).u0()).isLessThan(roof.u0());

		assertThat(PvPhysics.dcPower(10, 1000, 25, -0.4, 0)).isCloseTo(10, within(1e-12));
		assertThat(PvPhysics.dcPower(10, 1000, 45, -0.4, 0)).isCloseTo(9.2, within(1e-12));
		assertThat(PvPhysics.dcPower(10, 1000, 45, -0.4, 10)).isCloseTo(8.28, within(1e-12));
		assertThat(PvPhysics.dcPower(10, 0, 45, -0.4, 10)).isZero();

		assertThat(PvPhysics.acPower(9, 0.96, 8).power()).isEqualTo(8);
		assertThat(PvPhysics.acPower(9, 0.96, 8).clipped()).isTrue();
		assertThat(PvPhysics.acPower(5, 0.96, 8).power()).isCloseTo(4.8, within(1e-12));
		assertThat(PvPhysics.acPower(9, 0.96, 0).clipped()).as("no limit").isFalse();
	}

	@Test
	void horizonFromAForestAcrossNorth() {
		Plant plant = PvFactory.eINSTANCE.createPlant();
		plant.setMountingHeight(6);
		Obstacle forest = PvFactory.eINSTANCE.createObstacle();
		forest.setAzimuthFrom(200);
		forest.setAzimuthTo(260);
		forest.setDistance(40);
		forest.setHeight(25);
		plant.getObstacles().add(forest);
		Obstacle north = PvFactory.eINSTANCE.createObstacle();
		north.setAzimuthFrom(340);
		north.setAzimuthTo(20);
		north.setDistance(10);
		north.setHeight(16);
		plant.getObstacles().add(north);
		Horizon h = Horizon.of(plant);
		assertThat(h.at(230)).isCloseTo(Math.toDegrees(Math.atan(19.0 / 40)), within(1e-9));
		assertThat(h.at(180)).isZero();
		assertThat(h.at(0)).isCloseTo(45, within(1e-9));
		assertThat(h.at(350)).isCloseTo(45, within(1e-9));
		assertThat(h.at(30)).isZero();
		assertThat(h.hides(20, 230)).isTrue();
		assertThat(h.hides(30, 230)).isFalse();
		assertThat(h.skyViewLoss()).isBetween(0.01, 0.2);
		assertThat(Horizon.FLAT.skyViewLoss()).isZero();
		assertThat(Horizon.within(10, 340, 20)).isTrue();
		assertThat(Horizon.within(-10, 340, 20)).isTrue();
		assertThat(Horizon.within(100, 340, 20)).isFalse();
	}

	@Test
	void leaflessForestLetsPartOfTheSunThroughInWinterOnly() {
		Plant plant = PvFactory.eINSTANCE.createPlant();
		plant.setMountingHeight(3);
		Obstacle forest = PvFactory.eINSTANCE.createObstacle();
		forest.setAzimuthFrom(150);
		forest.setAzimuthTo(280);
		forest.setDistance(35);
		forest.setHeight(25);
		forest.setLeafOffTransmittance(0.3);
		plant.getObstacles().add(forest);
		Obstacle house = PvFactory.eINSTANCE.createObstacle();
		house.setAzimuthFrom(200);
		house.setAzimuthTo(210);
		house.setDistance(10);
		house.setHeight(8);
		plant.getObstacles().add(house);
		Horizon h = Horizon.of(plant);
		LocalDate october = LocalDate.of(2026, 10, 4);
		LocalDate december = LocalDate.of(2026, 12, 21);
		LocalDate may = LocalDate.of(2026, 5, 1);
		assertThat(h.beamShare(20, 180, october)).as("leaves on").isZero();
		assertThat(h.beamShare(15, 180, december)).as("bare crowns").isCloseTo(0.3, within(1e-9));
		assertThat(h.beamShare(15, 180, may)).as("leaves out again").isZero();
		assertThat(h.beamShare(20, 205, december)).as("the house is opaque all year").isZero();
		assertThat(h.beamShare(40, 180, october)).as("above the trees").isEqualTo(1);
		assertThat(Horizon.leafOff(LocalDate.of(2026, 11, 15))).isTrue();
		assertThat(Horizon.leafOff(LocalDate.of(2026, 11, 14))).isFalse();
		assertThat(Horizon.leafOff(LocalDate.of(2026, 4, 30))).isTrue();

		PlaneIrradiance free = PvPhysics.plane(400, 100, 15, 180, 10, 225, 0.2, I0, 1, 0);
		PlaneIrradiance thinned = PvPhysics.plane(400, 100, 15, 180, 10, 225, 0.2, I0, 0.3, 0);
		assertThat(thinned.beam()).isCloseTo(0.3 * free.beam(), within(1e-9));
	}
}
