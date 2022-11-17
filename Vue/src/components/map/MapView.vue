<template>
  <div class="map-container">
    <div :class="{ 'nav-open': isShow }" id="mySidebar" class="sidebar">
      <a href="javascript:void(0)" class="closebtn" @click="closeNav">×</a>
      <b-list-group v-if="aptInfoList.length > 0">
        <b-list-group-item href="#" class="d-flex justify-content-between">
          <span class="col-apt font-weight-bold">아파트명</span>
          <span class="col-build-year font-weight-bold">건축년도</span>
        </b-list-group-item>
        <map-view-row
          v-for="(info, index) in aptInfoList"
          :key="index"
          :info="info"
        ></map-view-row>
      </b-list-group>
      <template v-else>
        <div class="text-center">검색 결과가 없습니다.</div>
      </template>
    </div>
    <div class="wh100">
      <map-kakao @openNav="openNav"></map-kakao>
    </div>
  </div>
</template>

<script>
import MapKakao from "@/components/map/MapKakao.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    MapKakao,
    MapViewRow: () => import("@/components/map/MapViewRow.vue"),
  },
  data() {
    return {
      isShow: false,
    };
  },
  computed: {
    ...mapGetters(["aptInfoList"]),
  },
  methods: {
    closeNav() {
      this.isShow = false;
    },
    openNav() {
      this.isShow = true;
    },
  },
};
</script>

<style scoped>
@media screen and (max-height: 450px) {
  .sidebar {
    padding-top: 15px;
  }
  .sidebar a {
    font-size: 18px;
  }
}

.map-container {
  height: calc(100% - 50px);
  display: flex;
}
.sidebar {
  height: 100%;
  width: 0;
  position: relative;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: white;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}
.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

/* sidebar 클래스 */
.nav-open {
  width: 450px;
}
</style>
<style>
.col-apt {
  width: 70%;
}
.col-build-year {
  width: 30%;
  text-align: center;
}
</style>