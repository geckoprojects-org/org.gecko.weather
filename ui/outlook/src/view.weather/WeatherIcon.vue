<script setup lang="ts">
/** A line icon per sky, drawn in currentColor like the icons of xdp-ui */
import type { Sky } from './weather.js'

defineProps<{ sky: Sky; night?: boolean; size?: number }>()
</script>

<template>
  <svg
    :width="size ?? 28"
    :height="size ?? 28"
    viewBox="0 0 32 32"
    fill="none"
    stroke="currentColor"
    stroke-width="1.6"
    stroke-linecap="round"
    stroke-linejoin="round"
    aria-hidden="true"
  >
    <template v-if="sky === 'clear'">
      <path v-if="night" d="M20 6a10 10 0 1 0 6 16A8 8 0 0 1 20 6z" />
      <template v-else>
        <circle cx="16" cy="16" r="5.5" />
        <path d="M16 3v3M16 26v3M3 16h3M26 16h3M6.8 6.8l2.1 2.1M23.1 23.1l2.1 2.1M6.8 25.2l2.1-2.1M23.1 8.9l2.1-2.1" />
      </template>
    </template>
    <template v-else>
      <!-- the sun or moon peeking out behind the cloud -->
      <template v-if="sky === 'partly'">
        <path v-if="night" d="M15 4.5a6.5 6.5 0 1 0 6.8 8.3A5.2 5.2 0 0 1 15 4.5z" />
        <template v-else>
          <circle cx="12" cy="11" r="4.2" />
          <path d="M12 3.5v1.6M4.5 11h1.6M6.7 5.7l1.1 1.1M17.3 5.7l-1.1 1.1" />
        </template>
      </template>
      <path
        v-if="sky !== 'fog'"
        d="M10 24h13a5 5 0 0 0 .6-10 7 7 0 0 0-13.3 1.6A4.2 4.2 0 0 0 10 24z"
        :transform="sky === 'partly' ? 'translate(1.5 1.5)' : sky === 'cloudy' ? '' : 'translate(0 -4)'"
      />
      <path v-if="sky === 'fog'" d="M6 12h20M4 17h24M7 22h18M10 27h12" />
      <path v-if="sky === 'drizzle'" d="M11 24.5v.1M16 26v.1M21 24.5v.1M13.5 28.5v.1M18.5 28.5v.1" stroke-width="2.4" />
      <path v-if="sky === 'rain'" d="M11 23l-1.5 5M16 23l-1.5 5M21 23l-1.5 5" />
      <path v-if="sky === 'sleet'" d="M11 23l-1.5 5M21 23l-1.5 5M16 25.5v.1" stroke-width="1.8" />
      <path v-if="sky === 'snow'" d="M11 24v4M9 26h4M21 24v4M19 26h4M16 27v.1" />
      <path v-if="sky === 'thunder'" d="M17 21l-3 4.5h4l-3 4.5" />
    </template>
  </svg>
</template>
