<template>
  <div class="app-container">
    <map-filter></map-filter>
    <map-view
      :yearSelected="yearSelected"
      :monthSelected="monthSelected"></map-view>
  </div>
</template>

<script>
import MapFilter from "@/components/map/MapFilter.vue";
import MapView from "@/components/map/MapView.vue";

export default {
  data() {
    return {
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
  },
};
</script>

<style scoped></style>
