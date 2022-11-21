<template>
  <div class="map-container">
    <map-view-sidebar></map-view-sidebar>
    <div class="wh100">
      <map-kakao></map-kakao>
    </div>
  </div>
</template>

<script>
import MapKakao from "@/components/map/MapKakao.vue";
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  components: {
    MapKakao,
    MapViewSidebar: () => import("@/components/map/MapViewSidebar.vue"),
  },
  computed: {
    ...mapGetters(mapStore, ["aptInfoList"]),
  },
  methods: {
    ...mapActions(mapStore, ["updateAptInfoList"]),
  },
  created() {
    this.updateAptInfoList([]);
  },
};
</script>

<style scoped>
.map-container {
  height: calc(100% - 50px);
  position: relative;
  display: flex;
}
</style>
