<template>
  <div class="map-container">
    <map-view-sidebar :panelFlag="panelFlag"></map-view-sidebar>
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
  data() {
    return {
      panelFlag: false,
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptInfoList"]),
  },
  methods: {
    ...mapActions(mapStore, ["updateAptInfoList"]),
  },
  created() {
    this.updateAptInfoList([]);
    this.$on("onPanelOpen", () => {
      this.panelFlag = true;
    });
    this.$on("onPanelClose", () => {
      this.panelFlag = false;
    });
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
