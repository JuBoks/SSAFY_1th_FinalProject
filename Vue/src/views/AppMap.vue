<template>
  <div class="app-container">
    <map-filter @onPanelOpen="onPanelOpen"></map-filter>
    <map-view
      :panelOpen="panelOpen"
      :yearSelected="yearSelected"
      :monthSelected="monthSelected"
      @onPanelOpen="onPanelOpen"
      @onPanelClose="onPanelClose"
    ></map-view>
  </div>
</template>

<script>
import MapFilter from "@/components/map/MapFilter.vue";
import MapView from "@/components/map/MapView.vue";

export default {
  data() {
    return {
      panelOpen: false,
      yearSelected: null,
      monthSelected: null,
    };
  },
  components: {
    MapFilter,
    MapView,
  },
  methods: {
    setYear(v) {
      this.yearSelected = v;
    },
    setMonth(v) {
      this.monthSelected = v;
    },
    onPanelOpen() {
      this.panelOpen = true;
    },
    onPanelClose() {
      this.panelOpen = false;
    },
  },
};
</script>

<style scoped></style>
