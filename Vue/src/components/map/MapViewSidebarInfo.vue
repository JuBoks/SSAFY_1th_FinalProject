<template>
  <div class="rv-container">
    <div ref="rv" class="wh100"></div>
    <google-chart></google-chart>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  components: {
    "google-chart": () =>
      import("@/components/map/MapViewSidebarInfoChart.vue"),
  },
  computed: {
    ...mapGetters(mapStore, ["aptSelected"]),
  },
  mounted() {
    console.log("aptSelected", this.aptSelected);
    window.mapInstance &&
      window.mapInstance.createRoadView(
        this.$refs.rv,
        this.aptSelected.position
      );
  },
};
</script>

<style scoped>
.rv-container {
  height: 245px;
}
</style>
