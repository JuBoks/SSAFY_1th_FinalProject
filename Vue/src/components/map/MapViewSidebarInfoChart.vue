<template>
  <GChart :type="type" :data="data" :options="options" :events="events" />
</template>

<script>
import { GChart } from "vue-google-charts/legacy";

import { chartType, chartData, chartOptions, chartEvents } from "@/util/chart";

export default {
  name: "GoogleChart",
  components: {
    GChart,
  },
  data() {
    return {
      type: chartType,
      data: chartData,
      options: chartOptions,
      events: chartEvents,
    };
  },
  methods: {
    selectRow(el1, el2, el3) {
      console.log("select row", el1, el2, el3);
    },
  },
};
</script>
