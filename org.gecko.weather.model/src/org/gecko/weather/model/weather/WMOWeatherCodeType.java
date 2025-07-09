/*
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
 *      Mark Hoffmann - initial API and implementation
 */
package org.gecko.weather.model.weather;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>WMO Weather Code Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.gecko.weather.model.weather.WeatherPackage#getWMOWeatherCodeType()
 * @model
 * @generated
 */
@ProviderType
public enum WMOWeatherCodeType implements Enumerator {
	/**
	 * The '<em><b>W00</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No change in cloud cover (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W00_VALUE
	 * @generated
	 * @ordered
	 */
	W00(0, "W_00", "00"),

	/**
	 * The '<em><b>W01</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness is decreasing (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W01_VALUE
	 * @generated
	 * @ordered
	 */
	W01(1, "W_01", "01"),

	/**
	 * The '<em><b>W02</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness remains unchanged (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W02_VALUE
	 * @generated
	 * @ordered
	 */
	W02(2, "W_02", "02"),

	/**
	 * The '<em><b>W03</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness is increasing (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W03_VALUE
	 * @generated
	 * @ordered
	 */
	W03(3, "W_03", "03"),

	/**
	 * The '<em><b>W04</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Haze, smoke, dust, visibility ≥ 1 km
	 * <!-- end-model-doc -->
	 * @see #W04_VALUE
	 * @generated
	 * @ordered
	 */
	W04(4, "W_04", "04"),

	/**
	 * The '<em><b>W05</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Haze, smoke, dust, visibility < 1 km
	 * <!-- end-model-doc -->
	 * @see #W05_VALUE
	 * @generated
	 * @ordered
	 */
	W05(5, "W_05", "05"),

	/**
	 * The '<em><b>W10</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mist
	 * <!-- end-model-doc -->
	 * @see #W10_VALUE
	 * @generated
	 * @ordered
	 */
	W10(10, "W_10", "10"),

	/**
	 * The '<em><b>W20</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog
	 * <!-- end-model-doc -->
	 * @see #W20_VALUE
	 * @generated
	 * @ordered
	 */
	W20(20, "W_20", "20"),

	/**
	 * The '<em><b>W21</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation (recent, unspecified)
	 * <!-- end-model-doc -->
	 * @see #W21_VALUE
	 * @generated
	 * @ordered
	 */
	W21(21, "W_21", "21"),

	/**
	 * The '<em><b>W22</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle or snow grains (recent)
	 * <!-- end-model-doc -->
	 * @see #W22_VALUE
	 * @generated
	 * @ordered
	 */
	W22(22, "W_22", "22"),

	/**
	 * The '<em><b>W23</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain (recent)
	 * <!-- end-model-doc -->
	 * @see #W23_VALUE
	 * @generated
	 * @ordered
	 */
	W23(23, "W_23", "23"),

	/**
	 * The '<em><b>W24</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow (recent)
	 * <!-- end-model-doc -->
	 * @see #W24_VALUE
	 * @generated
	 * @ordered
	 */
	W24(24, "W_24", "24"),

	/**
	 * The '<em><b>W25</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain/drizzle (recent)
	 * <!-- end-model-doc -->
	 * @see #W25_VALUE
	 * @generated
	 * @ordered
	 */
	W25(25, "W_25", "25"),

	/**
	 * The '<em><b>W26</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm (recent)
	 * <!-- end-model-doc -->
	 * @see #W26_VALUE
	 * @generated
	 * @ordered
	 */
	W26(26, "W_26", "26"),

	/**
	 * The '<em><b>W27</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow or sand (recent)
	 * <!-- end-model-doc -->
	 * @see #W27_VALUE
	 * @generated
	 * @ordered
	 */
	W27(27, "W_27", "27"),

	/**
	 * The '<em><b>W28</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow/sand, V ≥ 1 km (recent)
	 * <!-- end-model-doc -->
	 * @see #W28_VALUE
	 * @generated
	 * @ordered
	 */
	W28(28, "W_28", "28"),

	/**
	 * The '<em><b>W29</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow/sand, V < 1 km (recent)
	 * <!-- end-model-doc -->
	 * @see #W29_VALUE
	 * @generated
	 * @ordered
	 */
	W29(29, "W_29", "29"),

	/**
	 * The '<em><b>W30</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog
	 * <!-- end-model-doc -->
	 * @see #W30_VALUE
	 * @generated
	 * @ordered
	 */
	W30(30, "W_30", "30"),

	/**
	 * The '<em><b>W31</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Patches of fog
	 * <!-- end-model-doc -->
	 * @see #W31_VALUE
	 * @generated
	 * @ordered
	 */
	W31(31, "W_31", "31"),

	/**
	 * The '<em><b>W32</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — thinning
	 * <!-- end-model-doc -->
	 * @see #W32_VALUE
	 * @generated
	 * @ordered
	 */
	W32(32, "W_32", "32"),

	/**
	 * The '<em><b>W33</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — no change
	 * <!-- end-model-doc -->
	 * @see #W33_VALUE
	 * @generated
	 * @ordered
	 */
	W33(33, "W_33", "33"),

	/**
	 * The '<em><b>W34</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — thickening
	 * <!-- end-model-doc -->
	 * @see #W34_VALUE
	 * @generated
	 * @ordered
	 */
	W34(34, "W_34", "34"),

	/**
	 * The '<em><b>W35</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rime fog
	 * <!-- end-model-doc -->
	 * @see #W35_VALUE
	 * @generated
	 * @ordered
	 */
	W35(35, "W_35", "35"),

	/**
	 * The '<em><b>W40</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation (present, unspecified)
	 * <!-- end-model-doc -->
	 * @see #W40_VALUE
	 * @generated
	 * @ordered
	 */
	W40(40, "W_40", "40"),

	/**
	 * The '<em><b>W41</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation slight/moderate
	 * <!-- end-model-doc -->
	 * @see #W41_VALUE
	 * @generated
	 * @ordered
	 */
	W41(41, "W_41", "41"),

	/**
	 * The '<em><b>W42</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation heavy
	 * <!-- end-model-doc -->
	 * @see #W42_VALUE
	 * @generated
	 * @ordered
	 */
	W42(42, "W_42", "42"),

	/**
	 * The '<em><b>W43</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Liquid precipitation slight/mod
	 * <!-- end-model-doc -->
	 * @see #W43_VALUE
	 * @generated
	 * @ordered
	 */
	W43(43, "W_43", "43"),

	/**
	 * The '<em><b>W44</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Liquid precipitation heavy
	 * <!-- end-model-doc -->
	 * @see #W44_VALUE
	 * @generated
	 * @ordered
	 */
	W44(44, "W_44", "44"),

	/**
	 * The '<em><b>W45</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solid precipitation slight/mod
	 * <!-- end-model-doc -->
	 * @see #W45_VALUE
	 * @generated
	 * @ordered
	 */
	W45(45, "W_45", "45"),

	/**
	 * The '<em><b>W50</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle
	 * <!-- end-model-doc -->
	 * @see #W50_VALUE
	 * @generated
	 * @ordered
	 */
	W50(50, "W_50", "50"),

	/**
	 * The '<em><b>W51</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle slight
	 * <!-- end-model-doc -->
	 * @see #W51_VALUE
	 * @generated
	 * @ordered
	 */
	W51(51, "W_51", "51"),

	/**
	 * The '<em><b>W52</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle moderate
	 * <!-- end-model-doc -->
	 * @see #W52_VALUE
	 * @generated
	 * @ordered
	 */
	W52(52, "W_52", "52"),

	/**
	 * The '<em><b>W53</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle heavy
	 * <!-- end-model-doc -->
	 * @see #W53_VALUE
	 * @generated
	 * @ordered
	 */
	W53(53, "W_53", "53"),

	/**
	 * The '<em><b>W54</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle slight
	 * <!-- end-model-doc -->
	 * @see #W54_VALUE
	 * @generated
	 * @ordered
	 */
	W54(54, "W_54", "54"),

	/**
	 * The '<em><b>W55</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle moderate
	 * <!-- end-model-doc -->
	 * @see #W55_VALUE
	 * @generated
	 * @ordered
	 */
	W55(55, "W_55", "55"),

	/**
	 * The '<em><b>W56</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle heavy
	 * <!-- end-model-doc -->
	 * @see #W56_VALUE
	 * @generated
	 * @ordered
	 */
	W56(56, "W_56", "56"),

	/**
	 * The '<em><b>W57</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle & rain slight
	 * <!-- end-model-doc -->
	 * @see #W57_VALUE
	 * @generated
	 * @ordered
	 */
	W57(57, "W_57", "57"),

	/**
	 * The '<em><b>W58</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle & rain mod/heavy
	 * <!-- end-model-doc -->
	 * @see #W58_VALUE
	 * @generated
	 * @ordered
	 */
	W58(58, "W_58", "58"),

	/**
	 * The '<em><b>W60</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain
	 * <!-- end-model-doc -->
	 * @see #W60_VALUE
	 * @generated
	 * @ordered
	 */
	W60(60, "W_60", "60"),

	/**
	 * The '<em><b>W61</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain slight
	 * <!-- end-model-doc -->
	 * @see #W61_VALUE
	 * @generated
	 * @ordered
	 */
	W61(61, "W_61", "61"),

	/**
	 * The '<em><b>W62</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain moderate
	 * <!-- end-model-doc -->
	 * @see #W62_VALUE
	 * @generated
	 * @ordered
	 */
	W62(62, "W_62", "62"),

	/**
	 * The '<em><b>W63</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain heavy
	 * <!-- end-model-doc -->
	 * @see #W63_VALUE
	 * @generated
	 * @ordered
	 */
	W63(63, "W_63", "63"),

	/**
	 * The '<em><b>W64</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain slight
	 * <!-- end-model-doc -->
	 * @see #W64_VALUE
	 * @generated
	 * @ordered
	 */
	W64(64, "W_64", "64"),

	/**
	 * The '<em><b>W65</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain moderate
	 * <!-- end-model-doc -->
	 * @see #W65_VALUE
	 * @generated
	 * @ordered
	 */
	W65(65, "W_65", "65"),

	/**
	 * The '<em><b>W66</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain heavy
	 * <!-- end-model-doc -->
	 * @see #W66_VALUE
	 * @generated
	 * @ordered
	 */
	W66(66, "W_66", "66"),

	/**
	 * The '<em><b>W67</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain + snow (or drizzle) slight
	 * <!-- end-model-doc -->
	 * @see #W67_VALUE
	 * @generated
	 * @ordered
	 */
	W67(67, "W_67", "67"),

	/**
	 * The '<em><b>W68</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain + snow mod/heavy
	 * <!-- end-model-doc -->
	 * @see #W68_VALUE
	 * @generated
	 * @ordered
	 */
	W68(68, "W_68", "68"),

	/**
	 * The '<em><b>W70</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow
	 * <!-- end-model-doc -->
	 * @see #W70_VALUE
	 * @generated
	 * @ordered
	 */
	W70(70, "W_70", "70"),

	/**
	 * The '<em><b>W71</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow slight
	 * <!-- end-model-doc -->
	 * @see #W71_VALUE
	 * @generated
	 * @ordered
	 */
	W71(71, "W_71", "71"),

	/**
	 * The '<em><b>W72</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow moderate
	 * <!-- end-model-doc -->
	 * @see #W72_VALUE
	 * @generated
	 * @ordered
	 */
	W72(72, "W_72", "72"),

	/**
	 * The '<em><b>W73</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow heavy
	 * <!-- end-model-doc -->
	 * @see #W73_VALUE
	 * @generated
	 * @ordered
	 */
	W73(73, "W_73", "73"),

	/**
	 * The '<em><b>W74</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets slight
	 * <!-- end-model-doc -->
	 * @see #W74_VALUE
	 * @generated
	 * @ordered
	 */
	W74(74, "W_74", "74"),

	/**
	 * The '<em><b>W75</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets moderate
	 * <!-- end-model-doc -->
	 * @see #W75_VALUE
	 * @generated
	 * @ordered
	 */
	W75(75, "W_75", "75"),

	/**
	 * The '<em><b>W76</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets heavy
	 * <!-- end-model-doc -->
	 * @see #W76_VALUE
	 * @generated
	 * @ordered
	 */
	W76(76, "W_76", "76"),

	/**
	 * The '<em><b>W77</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow grains
	 * <!-- end-model-doc -->
	 * @see #W77_VALUE
	 * @generated
	 * @ordered
	 */
	W77(77, "W_77", "77"),

	/**
	 * The '<em><b>W80</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Showers
	 * <!-- end-model-doc -->
	 * @see #W80_VALUE
	 * @generated
	 * @ordered
	 */
	W80(80, "W_80", "80"),

	/**
	 * The '<em><b>W81</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers slight
	 * <!-- end-model-doc -->
	 * @see #W81_VALUE
	 * @generated
	 * @ordered
	 */
	W81(81, "W_81", "81"),

	/**
	 * The '<em><b>W82</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers moderate
	 * <!-- end-model-doc -->
	 * @see #W82_VALUE
	 * @generated
	 * @ordered
	 */
	W82(82, "W_82", "82"),

	/**
	 * The '<em><b>W83</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers heavy
	 * <!-- end-model-doc -->
	 * @see #W83_VALUE
	 * @generated
	 * @ordered
	 */
	W83(83, "W_83", "83"),

	/**
	 * The '<em><b>W85</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers slight
	 * <!-- end-model-doc -->
	 * @see #W85_VALUE
	 * @generated
	 * @ordered
	 */
	W85(85, "W_85", "85"),

	/**
	 * The '<em><b>W86</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers moderate
	 * <!-- end-model-doc -->
	 * @see #W86_VALUE
	 * @generated
	 * @ordered
	 */
	W86(86, "W_86", "86"),

	/**
	 * The '<em><b>W87</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers heavy
	 * <!-- end-model-doc -->
	 * @see #W87_VALUE
	 * @generated
	 * @ordered
	 */
	W87(87, "W_87", "87"),

	/**
	 * The '<em><b>W90</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hail showers (no thunder)
	 * <!-- end-model-doc -->
	 * @see #W90_VALUE
	 * @generated
	 * @ordered
	 */
	W90(90, "W_90", "90"),

	/**
	 * The '<em><b>W91</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + slight rain
	 * <!-- end-model-doc -->
	 * @see #W91_VALUE
	 * @generated
	 * @ordered
	 */
	W91(91, "W_91", "91"),

	/**
	 * The '<em><b>W92</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + mod/heavy rain
	 * <!-- end-model-doc -->
	 * @see #W92_VALUE
	 * @generated
	 * @ordered
	 */
	W92(92, "W_92", "92"),

	/**
	 * The '<em><b>W93</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + slight snow/hail
	 * <!-- end-model-doc -->
	 * @see #W93_VALUE
	 * @generated
	 * @ordered
	 */
	W93(93, "W_93", "93"),

	/**
	 * The '<em><b>W94</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + mod/heavy snow/hail
	 * <!-- end-model-doc -->
	 * @see #W94_VALUE
	 * @generated
	 * @ordered
	 */
	W94(94, "W_94", "94"),

	/**
	 * The '<em><b>W95</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm, no hail (slight or moderate)
	 * <!-- end-model-doc -->
	 * @see #W95_VALUE
	 * @generated
	 * @ordered
	 */
	W95(95, "W_95", "95"),

	/**
	 * The '<em><b>W96</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with slight hail
	 * <!-- end-model-doc -->
	 * @see #W96_VALUE
	 * @generated
	 * @ordered
	 */
	W96(96, "W_96", "96"),

	/**
	 * The '<em><b>W97</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm, heavy, no hail
	 * <!-- end-model-doc -->
	 * @see #W97_VALUE
	 * @generated
	 * @ordered
	 */
	W97(97, "W_97", "97"),

	/**
	 * The '<em><b>W98</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with dust or sandstorm
	 * <!-- end-model-doc -->
	 * @see #W98_VALUE
	 * @generated
	 * @ordered
	 */
	W98(98, "W_98", "98"),

	/**
	 * The '<em><b>W99</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with heavy hail
	 * <!-- end-model-doc -->
	 * @see #W99_VALUE
	 * @generated
	 * @ordered
	 */
	W99(99, "W_99", "99"),

	/**
	 * The '<em><b>WUNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No value was set
	 * <!-- end-model-doc -->
	 * @see #WUNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	WUNKNOWN(1000, "W_UNKNOWN", "UNKNOWN");

	/**
	 * The '<em><b>W00</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No change in cloud cover (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W00
	 * @model name="W_00" literal="00"
	 * @generated
	 * @ordered
	 */
	public static final int W00_VALUE = 0;

	/**
	 * The '<em><b>W01</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness is decreasing (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W01
	 * @model name="W_01" literal="01"
	 * @generated
	 * @ordered
	 */
	public static final int W01_VALUE = 1;

	/**
	 * The '<em><b>W02</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness remains unchanged (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W02
	 * @model name="W_02" literal="02"
	 * @generated
	 * @ordered
	 */
	public static final int W02_VALUE = 2;

	/**
	 * The '<em><b>W03</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cloudiness is increasing (MOSMIX-specific)
	 * <!-- end-model-doc -->
	 * @see #W03
	 * @model name="W_03" literal="03"
	 * @generated
	 * @ordered
	 */
	public static final int W03_VALUE = 3;

	/**
	 * The '<em><b>W04</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Haze, smoke, dust, visibility ≥ 1 km
	 * <!-- end-model-doc -->
	 * @see #W04
	 * @model name="W_04" literal="04"
	 * @generated
	 * @ordered
	 */
	public static final int W04_VALUE = 4;

	/**
	 * The '<em><b>W05</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Haze, smoke, dust, visibility < 1 km
	 * <!-- end-model-doc -->
	 * @see #W05
	 * @model name="W_05" literal="05"
	 * @generated
	 * @ordered
	 */
	public static final int W05_VALUE = 5;

	/**
	 * The '<em><b>W10</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mist
	 * <!-- end-model-doc -->
	 * @see #W10
	 * @model name="W_10" literal="10"
	 * @generated
	 * @ordered
	 */
	public static final int W10_VALUE = 10;

	/**
	 * The '<em><b>W20</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog
	 * <!-- end-model-doc -->
	 * @see #W20
	 * @model name="W_20" literal="20"
	 * @generated
	 * @ordered
	 */
	public static final int W20_VALUE = 20;

	/**
	 * The '<em><b>W21</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation (recent, unspecified)
	 * <!-- end-model-doc -->
	 * @see #W21
	 * @model name="W_21" literal="21"
	 * @generated
	 * @ordered
	 */
	public static final int W21_VALUE = 21;

	/**
	 * The '<em><b>W22</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle or snow grains (recent)
	 * <!-- end-model-doc -->
	 * @see #W22
	 * @model name="W_22" literal="22"
	 * @generated
	 * @ordered
	 */
	public static final int W22_VALUE = 22;

	/**
	 * The '<em><b>W23</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain (recent)
	 * <!-- end-model-doc -->
	 * @see #W23
	 * @model name="W_23" literal="23"
	 * @generated
	 * @ordered
	 */
	public static final int W23_VALUE = 23;

	/**
	 * The '<em><b>W24</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow (recent)
	 * <!-- end-model-doc -->
	 * @see #W24
	 * @model name="W_24" literal="24"
	 * @generated
	 * @ordered
	 */
	public static final int W24_VALUE = 24;

	/**
	 * The '<em><b>W25</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain/drizzle (recent)
	 * <!-- end-model-doc -->
	 * @see #W25
	 * @model name="W_25" literal="25"
	 * @generated
	 * @ordered
	 */
	public static final int W25_VALUE = 25;

	/**
	 * The '<em><b>W26</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm (recent)
	 * <!-- end-model-doc -->
	 * @see #W26
	 * @model name="W_26" literal="26"
	 * @generated
	 * @ordered
	 */
	public static final int W26_VALUE = 26;

	/**
	 * The '<em><b>W27</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow or sand (recent)
	 * <!-- end-model-doc -->
	 * @see #W27
	 * @model name="W_27" literal="27"
	 * @generated
	 * @ordered
	 */
	public static final int W27_VALUE = 27;

	/**
	 * The '<em><b>W28</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow/sand, V ≥ 1 km (recent)
	 * <!-- end-model-doc -->
	 * @see #W28
	 * @model name="W_28" literal="28"
	 * @generated
	 * @ordered
	 */
	public static final int W28_VALUE = 28;

	/**
	 * The '<em><b>W29</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Blowing snow/sand, V < 1 km (recent)
	 * <!-- end-model-doc -->
	 * @see #W29
	 * @model name="W_29" literal="29"
	 * @generated
	 * @ordered
	 */
	public static final int W29_VALUE = 29;

	/**
	 * The '<em><b>W30</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog
	 * <!-- end-model-doc -->
	 * @see #W30
	 * @model name="W_30" literal="30"
	 * @generated
	 * @ordered
	 */
	public static final int W30_VALUE = 30;

	/**
	 * The '<em><b>W31</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Patches of fog
	 * <!-- end-model-doc -->
	 * @see #W31
	 * @model name="W_31" literal="31"
	 * @generated
	 * @ordered
	 */
	public static final int W31_VALUE = 31;

	/**
	 * The '<em><b>W32</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — thinning
	 * <!-- end-model-doc -->
	 * @see #W32
	 * @model name="W_32" literal="32"
	 * @generated
	 * @ordered
	 */
	public static final int W32_VALUE = 32;

	/**
	 * The '<em><b>W33</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — no change
	 * <!-- end-model-doc -->
	 * @see #W33
	 * @model name="W_33" literal="33"
	 * @generated
	 * @ordered
	 */
	public static final int W33_VALUE = 33;

	/**
	 * The '<em><b>W34</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fog — thickening
	 * <!-- end-model-doc -->
	 * @see #W34
	 * @model name="W_34" literal="34"
	 * @generated
	 * @ordered
	 */
	public static final int W34_VALUE = 34;

	/**
	 * The '<em><b>W35</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rime fog
	 * <!-- end-model-doc -->
	 * @see #W35
	 * @model name="W_35" literal="35"
	 * @generated
	 * @ordered
	 */
	public static final int W35_VALUE = 35;

	/**
	 * The '<em><b>W40</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation (present, unspecified)
	 * <!-- end-model-doc -->
	 * @see #W40
	 * @model name="W_40" literal="40"
	 * @generated
	 * @ordered
	 */
	public static final int W40_VALUE = 40;

	/**
	 * The '<em><b>W41</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation slight/moderate
	 * <!-- end-model-doc -->
	 * @see #W41
	 * @model name="W_41" literal="41"
	 * @generated
	 * @ordered
	 */
	public static final int W41_VALUE = 41;

	/**
	 * The '<em><b>W42</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Precipitation heavy
	 * <!-- end-model-doc -->
	 * @see #W42
	 * @model name="W_42" literal="42"
	 * @generated
	 * @ordered
	 */
	public static final int W42_VALUE = 42;

	/**
	 * The '<em><b>W43</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Liquid precipitation slight/mod
	 * <!-- end-model-doc -->
	 * @see #W43
	 * @model name="W_43" literal="43"
	 * @generated
	 * @ordered
	 */
	public static final int W43_VALUE = 43;

	/**
	 * The '<em><b>W44</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Liquid precipitation heavy
	 * <!-- end-model-doc -->
	 * @see #W44
	 * @model name="W_44" literal="44"
	 * @generated
	 * @ordered
	 */
	public static final int W44_VALUE = 44;

	/**
	 * The '<em><b>W45</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Solid precipitation slight/mod
	 * <!-- end-model-doc -->
	 * @see #W45
	 * @model name="W_45" literal="45"
	 * @generated
	 * @ordered
	 */
	public static final int W45_VALUE = 45;

	/**
	 * The '<em><b>W50</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle
	 * <!-- end-model-doc -->
	 * @see #W50
	 * @model name="W_50" literal="50"
	 * @generated
	 * @ordered
	 */
	public static final int W50_VALUE = 50;

	/**
	 * The '<em><b>W51</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle slight
	 * <!-- end-model-doc -->
	 * @see #W51
	 * @model name="W_51" literal="51"
	 * @generated
	 * @ordered
	 */
	public static final int W51_VALUE = 51;

	/**
	 * The '<em><b>W52</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle moderate
	 * <!-- end-model-doc -->
	 * @see #W52
	 * @model name="W_52" literal="52"
	 * @generated
	 * @ordered
	 */
	public static final int W52_VALUE = 52;

	/**
	 * The '<em><b>W53</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle heavy
	 * <!-- end-model-doc -->
	 * @see #W53
	 * @model name="W_53" literal="53"
	 * @generated
	 * @ordered
	 */
	public static final int W53_VALUE = 53;

	/**
	 * The '<em><b>W54</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle slight
	 * <!-- end-model-doc -->
	 * @see #W54
	 * @model name="W_54" literal="54"
	 * @generated
	 * @ordered
	 */
	public static final int W54_VALUE = 54;

	/**
	 * The '<em><b>W55</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle moderate
	 * <!-- end-model-doc -->
	 * @see #W55
	 * @model name="W_55" literal="55"
	 * @generated
	 * @ordered
	 */
	public static final int W55_VALUE = 55;

	/**
	 * The '<em><b>W56</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing drizzle heavy
	 * <!-- end-model-doc -->
	 * @see #W56
	 * @model name="W_56" literal="56"
	 * @generated
	 * @ordered
	 */
	public static final int W56_VALUE = 56;

	/**
	 * The '<em><b>W57</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle & rain slight
	 * <!-- end-model-doc -->
	 * @see #W57
	 * @model name="W_57" literal="57"
	 * @generated
	 * @ordered
	 */
	public static final int W57_VALUE = 57;

	/**
	 * The '<em><b>W58</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Drizzle & rain mod/heavy
	 * <!-- end-model-doc -->
	 * @see #W58
	 * @model name="W_58" literal="58"
	 * @generated
	 * @ordered
	 */
	public static final int W58_VALUE = 58;

	/**
	 * The '<em><b>W60</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain
	 * <!-- end-model-doc -->
	 * @see #W60
	 * @model name="W_60" literal="60"
	 * @generated
	 * @ordered
	 */
	public static final int W60_VALUE = 60;

	/**
	 * The '<em><b>W61</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain slight
	 * <!-- end-model-doc -->
	 * @see #W61
	 * @model name="W_61" literal="61"
	 * @generated
	 * @ordered
	 */
	public static final int W61_VALUE = 61;

	/**
	 * The '<em><b>W62</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain moderate
	 * <!-- end-model-doc -->
	 * @see #W62
	 * @model name="W_62" literal="62"
	 * @generated
	 * @ordered
	 */
	public static final int W62_VALUE = 62;

	/**
	 * The '<em><b>W63</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain heavy
	 * <!-- end-model-doc -->
	 * @see #W63
	 * @model name="W_63" literal="63"
	 * @generated
	 * @ordered
	 */
	public static final int W63_VALUE = 63;

	/**
	 * The '<em><b>W64</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain slight
	 * <!-- end-model-doc -->
	 * @see #W64
	 * @model name="W_64" literal="64"
	 * @generated
	 * @ordered
	 */
	public static final int W64_VALUE = 64;

	/**
	 * The '<em><b>W65</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain moderate
	 * <!-- end-model-doc -->
	 * @see #W65
	 * @model name="W_65" literal="65"
	 * @generated
	 * @ordered
	 */
	public static final int W65_VALUE = 65;

	/**
	 * The '<em><b>W66</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Freezing rain heavy
	 * <!-- end-model-doc -->
	 * @see #W66
	 * @model name="W_66" literal="66"
	 * @generated
	 * @ordered
	 */
	public static final int W66_VALUE = 66;

	/**
	 * The '<em><b>W67</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain + snow (or drizzle) slight
	 * <!-- end-model-doc -->
	 * @see #W67
	 * @model name="W_67" literal="67"
	 * @generated
	 * @ordered
	 */
	public static final int W67_VALUE = 67;

	/**
	 * The '<em><b>W68</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain + snow mod/heavy
	 * <!-- end-model-doc -->
	 * @see #W68
	 * @model name="W_68" literal="68"
	 * @generated
	 * @ordered
	 */
	public static final int W68_VALUE = 68;

	/**
	 * The '<em><b>W70</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow
	 * <!-- end-model-doc -->
	 * @see #W70
	 * @model name="W_70" literal="70"
	 * @generated
	 * @ordered
	 */
	public static final int W70_VALUE = 70;

	/**
	 * The '<em><b>W71</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow slight
	 * <!-- end-model-doc -->
	 * @see #W71
	 * @model name="W_71" literal="71"
	 * @generated
	 * @ordered
	 */
	public static final int W71_VALUE = 71;

	/**
	 * The '<em><b>W72</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow moderate
	 * <!-- end-model-doc -->
	 * @see #W72
	 * @model name="W_72" literal="72"
	 * @generated
	 * @ordered
	 */
	public static final int W72_VALUE = 72;

	/**
	 * The '<em><b>W73</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow heavy
	 * <!-- end-model-doc -->
	 * @see #W73
	 * @model name="W_73" literal="73"
	 * @generated
	 * @ordered
	 */
	public static final int W73_VALUE = 73;

	/**
	 * The '<em><b>W74</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets slight
	 * <!-- end-model-doc -->
	 * @see #W74
	 * @model name="W_74" literal="74"
	 * @generated
	 * @ordered
	 */
	public static final int W74_VALUE = 74;

	/**
	 * The '<em><b>W75</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets moderate
	 * <!-- end-model-doc -->
	 * @see #W75
	 * @model name="W_75" literal="75"
	 * @generated
	 * @ordered
	 */
	public static final int W75_VALUE = 75;

	/**
	 * The '<em><b>W76</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ice pellets heavy
	 * <!-- end-model-doc -->
	 * @see #W76
	 * @model name="W_76" literal="76"
	 * @generated
	 * @ordered
	 */
	public static final int W76_VALUE = 76;

	/**
	 * The '<em><b>W77</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow grains
	 * <!-- end-model-doc -->
	 * @see #W77
	 * @model name="W_77" literal="77"
	 * @generated
	 * @ordered
	 */
	public static final int W77_VALUE = 77;

	/**
	 * The '<em><b>W80</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Showers
	 * <!-- end-model-doc -->
	 * @see #W80
	 * @model name="W_80" literal="80"
	 * @generated
	 * @ordered
	 */
	public static final int W80_VALUE = 80;

	/**
	 * The '<em><b>W81</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers slight
	 * <!-- end-model-doc -->
	 * @see #W81
	 * @model name="W_81" literal="81"
	 * @generated
	 * @ordered
	 */
	public static final int W81_VALUE = 81;

	/**
	 * The '<em><b>W82</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers moderate
	 * <!-- end-model-doc -->
	 * @see #W82
	 * @model name="W_82" literal="82"
	 * @generated
	 * @ordered
	 */
	public static final int W82_VALUE = 82;

	/**
	 * The '<em><b>W83</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rain showers heavy
	 * <!-- end-model-doc -->
	 * @see #W83
	 * @model name="W_83" literal="83"
	 * @generated
	 * @ordered
	 */
	public static final int W83_VALUE = 83;

	/**
	 * The '<em><b>W85</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers slight
	 * <!-- end-model-doc -->
	 * @see #W85
	 * @model name="W_85" literal="85"
	 * @generated
	 * @ordered
	 */
	public static final int W85_VALUE = 85;

	/**
	 * The '<em><b>W86</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers moderate
	 * <!-- end-model-doc -->
	 * @see #W86
	 * @model name="W_86" literal="86"
	 * @generated
	 * @ordered
	 */
	public static final int W86_VALUE = 86;

	/**
	 * The '<em><b>W87</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Snow showers heavy
	 * <!-- end-model-doc -->
	 * @see #W87
	 * @model name="W_87" literal="87"
	 * @generated
	 * @ordered
	 */
	public static final int W87_VALUE = 87;

	/**
	 * The '<em><b>W90</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Hail showers (no thunder)
	 * <!-- end-model-doc -->
	 * @see #W90
	 * @model name="W_90" literal="90"
	 * @generated
	 * @ordered
	 */
	public static final int W90_VALUE = 90;

	/**
	 * The '<em><b>W91</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + slight rain
	 * <!-- end-model-doc -->
	 * @see #W91
	 * @model name="W_91" literal="91"
	 * @generated
	 * @ordered
	 */
	public static final int W91_VALUE = 91;

	/**
	 * The '<em><b>W92</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + mod/heavy rain
	 * <!-- end-model-doc -->
	 * @see #W92
	 * @model name="W_92" literal="92"
	 * @generated
	 * @ordered
	 */
	public static final int W92_VALUE = 92;

	/**
	 * The '<em><b>W93</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + slight snow/hail
	 * <!-- end-model-doc -->
	 * @see #W93
	 * @model name="W_93" literal="93"
	 * @generated
	 * @ordered
	 */
	public static final int W93_VALUE = 93;

	/**
	 * The '<em><b>W94</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm recent + mod/heavy snow/hail
	 * <!-- end-model-doc -->
	 * @see #W94
	 * @model name="W_94" literal="94"
	 * @generated
	 * @ordered
	 */
	public static final int W94_VALUE = 94;

	/**
	 * The '<em><b>W95</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm, no hail (slight or moderate)
	 * <!-- end-model-doc -->
	 * @see #W95
	 * @model name="W_95" literal="95"
	 * @generated
	 * @ordered
	 */
	public static final int W95_VALUE = 95;

	/**
	 * The '<em><b>W96</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with slight hail
	 * <!-- end-model-doc -->
	 * @see #W96
	 * @model name="W_96" literal="96"
	 * @generated
	 * @ordered
	 */
	public static final int W96_VALUE = 96;

	/**
	 * The '<em><b>W97</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm, heavy, no hail
	 * <!-- end-model-doc -->
	 * @see #W97
	 * @model name="W_97" literal="97"
	 * @generated
	 * @ordered
	 */
	public static final int W97_VALUE = 97;

	/**
	 * The '<em><b>W98</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with dust or sandstorm
	 * <!-- end-model-doc -->
	 * @see #W98
	 * @model name="W_98" literal="98"
	 * @generated
	 * @ordered
	 */
	public static final int W98_VALUE = 98;

	/**
	 * The '<em><b>W99</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Thunderstorm with heavy hail
	 * <!-- end-model-doc -->
	 * @see #W99
	 * @model name="W_99" literal="99"
	 * @generated
	 * @ordered
	 */
	public static final int W99_VALUE = 99;

	/**
	 * The '<em><b>WUNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No value was set
	 * <!-- end-model-doc -->
	 * @see #WUNKNOWN
	 * @model name="W_UNKNOWN" literal="UNKNOWN"
	 * @generated
	 * @ordered
	 */
	public static final int WUNKNOWN_VALUE = 1000;

	/**
	 * An array of all the '<em><b>WMO Weather Code Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WMOWeatherCodeType[] VALUES_ARRAY =
		new WMOWeatherCodeType[] {
			W00,
			W01,
			W02,
			W03,
			W04,
			W05,
			W10,
			W20,
			W21,
			W22,
			W23,
			W24,
			W25,
			W26,
			W27,
			W28,
			W29,
			W30,
			W31,
			W32,
			W33,
			W34,
			W35,
			W40,
			W41,
			W42,
			W43,
			W44,
			W45,
			W50,
			W51,
			W52,
			W53,
			W54,
			W55,
			W56,
			W57,
			W58,
			W60,
			W61,
			W62,
			W63,
			W64,
			W65,
			W66,
			W67,
			W68,
			W70,
			W71,
			W72,
			W73,
			W74,
			W75,
			W76,
			W77,
			W80,
			W81,
			W82,
			W83,
			W85,
			W86,
			W87,
			W90,
			W91,
			W92,
			W93,
			W94,
			W95,
			W96,
			W97,
			W98,
			W99,
			WUNKNOWN,
		};

	/**
	 * A public read-only list of all the '<em><b>WMO Weather Code Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WMOWeatherCodeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>WMO Weather Code Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WMOWeatherCodeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WMOWeatherCodeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WMO Weather Code Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WMOWeatherCodeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WMOWeatherCodeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WMO Weather Code Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WMOWeatherCodeType get(int value) {
		switch (value) {
			case W00_VALUE: return W00;
			case W01_VALUE: return W01;
			case W02_VALUE: return W02;
			case W03_VALUE: return W03;
			case W04_VALUE: return W04;
			case W05_VALUE: return W05;
			case W10_VALUE: return W10;
			case W20_VALUE: return W20;
			case W21_VALUE: return W21;
			case W22_VALUE: return W22;
			case W23_VALUE: return W23;
			case W24_VALUE: return W24;
			case W25_VALUE: return W25;
			case W26_VALUE: return W26;
			case W27_VALUE: return W27;
			case W28_VALUE: return W28;
			case W29_VALUE: return W29;
			case W30_VALUE: return W30;
			case W31_VALUE: return W31;
			case W32_VALUE: return W32;
			case W33_VALUE: return W33;
			case W34_VALUE: return W34;
			case W35_VALUE: return W35;
			case W40_VALUE: return W40;
			case W41_VALUE: return W41;
			case W42_VALUE: return W42;
			case W43_VALUE: return W43;
			case W44_VALUE: return W44;
			case W45_VALUE: return W45;
			case W50_VALUE: return W50;
			case W51_VALUE: return W51;
			case W52_VALUE: return W52;
			case W53_VALUE: return W53;
			case W54_VALUE: return W54;
			case W55_VALUE: return W55;
			case W56_VALUE: return W56;
			case W57_VALUE: return W57;
			case W58_VALUE: return W58;
			case W60_VALUE: return W60;
			case W61_VALUE: return W61;
			case W62_VALUE: return W62;
			case W63_VALUE: return W63;
			case W64_VALUE: return W64;
			case W65_VALUE: return W65;
			case W66_VALUE: return W66;
			case W67_VALUE: return W67;
			case W68_VALUE: return W68;
			case W70_VALUE: return W70;
			case W71_VALUE: return W71;
			case W72_VALUE: return W72;
			case W73_VALUE: return W73;
			case W74_VALUE: return W74;
			case W75_VALUE: return W75;
			case W76_VALUE: return W76;
			case W77_VALUE: return W77;
			case W80_VALUE: return W80;
			case W81_VALUE: return W81;
			case W82_VALUE: return W82;
			case W83_VALUE: return W83;
			case W85_VALUE: return W85;
			case W86_VALUE: return W86;
			case W87_VALUE: return W87;
			case W90_VALUE: return W90;
			case W91_VALUE: return W91;
			case W92_VALUE: return W92;
			case W93_VALUE: return W93;
			case W94_VALUE: return W94;
			case W95_VALUE: return W95;
			case W96_VALUE: return W96;
			case W97_VALUE: return W97;
			case W98_VALUE: return W98;
			case W99_VALUE: return W99;
			case WUNKNOWN_VALUE: return WUNKNOWN;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private WMOWeatherCodeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //WMOWeatherCodeType
