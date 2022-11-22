<template>
  <div class="rv-container">
    <div ref="rv" class="wh100"></div>
    <google-chart></google-chart>
    <map-view-sidebar-info-table></map-view-sidebar-info-table>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  components: {
    "google-chart": () =>
      import("@/components/map/MapViewSidebarInfoChart.vue"),
    "map-view-sidebar-info-table": () =>
      import("@/components/map/MapViewSidebarInfoTable.vue"),
  },
  computed: {
    ...mapGetters(mapStore, ["aptSelected"]),
  },
  mounted() {
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
