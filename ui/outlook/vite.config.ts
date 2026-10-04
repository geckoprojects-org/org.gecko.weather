/**
 * The demo host. The DDSR broker and the weather runtime speak no CORS, so the page reaches both
 * under its own origin — the same trick as xdp-ui with the otel-demo inventory: the weather
 * runtime announces this origin as its public URL (WEATHER_PUBLIC_URL), which makes the address
 * the broker hands out one the browser may call.
 */
import { resolve } from 'node:path'
import vue from '@vitejs/plugin-vue'
import { defineConfig } from 'vitest/config'

export default defineConfig({
  plugins: [vue()],
  // The DDSR client fingerprints with node:crypto; the shim (copied from xdp-ui) does the one hash it asks for.
  resolve: { alias: { 'node:crypto': resolve(import.meta.dirname, 'shims/node-crypto.ts') } },
  build: { target: 'es2022' },
  server: {
    port: 5181,
    // the outlook.ecore is read from the Java bundle — one file for both sides
    fs: { allow: [resolve(import.meta.dirname, '../..')] },
    proxy: {
      '/ddsr': process.env.DDSR_BROKER ?? 'http://localhost:8887',
      '/weather': process.env.WEATHER_RUNTIME ?? 'http://localhost:9093',
    },
  },
  preview: {
    port: 5181,
    proxy: {
      '/ddsr': process.env.DDSR_BROKER ?? 'http://localhost:8887',
      '/weather': process.env.WEATHER_RUNTIME ?? 'http://localhost:9093',
    },
  },
  test: { environment: 'node' },
})
