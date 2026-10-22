/*
 * Copyright (c) 2026 Data In Motion and others.
 * SPDX-License-Identifier: EPL-2.0
 */
/**
 * The demo host: the weather view, its service from the registry, examples as the fallback. In
 * xdp-ui the shell does this — it finds the views under XDP_VIEW and hands them their services.
 */
import { createApp, h } from 'vue'
import './styles/index.css'
import App from './App.vue'
import { DdsrWeatherOutlook } from './weather.ddsr/DdsrWeatherOutlook.js'
import { SampleWeatherOutlook } from './weather.ddsr/SampleWeatherOutlook.js'

const params = new URLSearchParams(window.location.search)
const theme = params.get('theme') ?? (window.matchMedia('(prefers-color-scheme: light)').matches ? 'light' : 'dark')
document.documentElement.dataset.theme = theme

const brokerUrl = params.get('broker') ?? new URL('/ddsr/rest', window.location.origin).toString()
const examples = params.has('examples')
const outlook = examples ? new SampleWeatherOutlook() : new DdsrWeatherOutlook(brokerUrl)

createApp({
  render: () => h(App, { outlook, outlookFallback: new SampleWeatherOutlook() }),
}).mount('#app')
