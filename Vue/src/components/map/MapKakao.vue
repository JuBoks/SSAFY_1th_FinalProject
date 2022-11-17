<template>
  <div class="map-wrap wh100">
    <b-form inline class="map-search">
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedSido"
        value-field="value"
        text-field="text"
        :options="sido"
        :text="selectedSidoName"
        @change="onSidoChanged"></b-form-select>
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedGugun"
        value-field="value"
        text-field="text"
        :options="gugun"
        :value="null"
        @change="onGugunChanged"></b-form-select>
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedDong"
        value-field="value"
        text-field="text"
        :options="dong"
        :value="null"
        @change="onDongChanged"></b-form-select>
      <!-- <b-button @click="search()">검색</b-button> -->
    </b-form>

    <div id="map" style="width: 100%; height: 100%"></div>
  </div>
</template>

<script>
import * as http from "@/util/http-common";
import * as Kakao from "@/util/kakao.js";
import { mapActions } from "vuex";

const mapStore = "mapStore";

export default {
  props: {
    yearSelected: Number,
    monthSelected: Number,
  },
  name: "KakaoMap",
  data() {
    return {
      sido: [{ text: "시/도", value: null }],
      gugun: [{ text: "구/군", value: null }],
      dong: [{ text: "동", value: null }],

      selectedSidoName: null,
      selectedSido: null,
      selectedGugun: null,
      selectedDong: null,
    };
  },
  async mounted() {
    // 시/도 초기화
    const sidoData = await http.get("/area/sido");
    sidoData.forEach((el) => {
      this.sido.push({
        text: el.sidoName,
        value: {
          code: el.dongCode,
          name: el.sidoName,
        },
      });
    });

    // 카카오 맵 생성
    this.map = new Kakao.Map();
    window.mapInstance = this.map;
    //this.map.setCenterAddr();
  },
  methods: {
    ...mapActions(mapStore, ["updateAptInfoList"]),

    async onSidoChanged() {
      this.gugun = [{ text: "구/군", value: null }];
      this.selectedGugun = null;
      this.dong = [{ text: "동", value: null }];
      this.selectedDong = null;

      const gugunData = await http.get(
        `/area/gugun?sidoCode=${this.selectedSido.code}`
      );
      gugunData.forEach((el) => {
        this.gugun.push({
          text: el.gugunName,
          value: {
            code: el.dongCode,
            name: el.gugunName,
          },
        });
      });
    },
    async onGugunChanged() {
      this.dong = [{ text: "동", value: null }];
      this.selectedDong = null;

      const dongData = await http.get(
        `/area/dong?gugunCode=${this.selectedGugun.code}`
      );
      dongData.forEach((el) => {
        this.dong.push({
          text: el.dongName,
          value: {
            code: el.dongCode,
            name: el.dongName,
          },
        });
      });
    },
    onDongChanged() {
      this.search();
    },
    validateSearch() {
      let err = true;
      let msg = "";
      !this.selectedSido && ((msg = "시/도를 선택해주세요."), (err = false));
      err &&
        !this.selectedGugun &&
        ((msg = "구/군을 선택해주세요."), (err = false));
      err &&
        !this.selectedDong &&
        ((msg = "동을 선택해주세요."), (err = false));

      if (!err) {
        alert(msg);
        return false;
      } else {
        return true;
      }
    },
    async getAptData() {
      const result = await http.get(`/aptinfo/${this.selectedDong.code}`);
      return result;
    },
    async getAllianceData() {
      const regCode = this.selectedGugun.code.substr(0, 5);
      const result = await http.get(
        `/alliance/list?sigunguCode=${regCode}&dealYear=${this.yearSelected}&dealMonth=${this.monthSelected}`
      );
      const items = result.response.body.items;
      return items == "" ? [] : items.item;
    },
    async search() {
      // 1. validation
      if (!this.validateSearch()) return;

      // 2. 아파트들 데이터 가져오기
      const [aptData] = await Promise.all([
        this.getAptData(),
        //this.getAllianceData(),
      ]);

      // 3. 좌측 리스트 정보 출력
      this.updateAptInfoList(aptData);

      console.log("아파트", aptData);

      // 4. 맵에 마커 표시하기
      if (aptData.length > 0) {
        // 매물정보가 있으면 마커가 다 표시할 수 있도록 맵을 이동
        const aptMarkers = await Kakao.AptMarkers(aptData);
        this.map.addAptCluster(aptMarkers);
      } else {
        // 매물정보가 없으면 임의로 이동
        const addr = `${this.selectedSido.name} ${this.selectedGugun.name} ${this.selectedDong.name}`;
        this.map.setCenterAddr(addr, 3);
      }

      this.$emit("openNav", true);
    },
    // initMap() {
    //   const container = document.getElementById("map");
    //   const options = {
    //     center: new kakao.maps.LatLng(33.450701, 126.570667),
    //     level: 5,
    //   };
    //   //지도 객체를 등록합니다.
    //   //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
    //   this.map = new kakao.maps.Map(container, options);
    // },
    // changeSize(size) {
    //   const container = document.getElementById("map");
    //   container.style.width = `${size}px`;
    //   container.style.height = `${size}px`;
    //   this.map.relayout();
    // },
    // displayMarker(markerPositions) {
    //   if (this.markers.length > 0) {
    //     this.markers.forEach((marker) => marker.setMap(null));
    //   }
    //   const positions = markerPositions.map(
    //     (position) => new kakao.maps.LatLng(...position)
    //   );
    //   if (positions.length > 0) {
    //     this.markers = positions.map(
    //       (position) =>
    //         new kakao.maps.Marker({
    //           map: this.map,
    //           position,
    //         })
    //     );
    //     const bounds = positions.reduce(
    //       (bounds, latlng) => bounds.extend(latlng),
    //       new kakao.maps.LatLngBounds()
    //     );
    //     this.map.setBounds(bounds);
    //   }
    // },
    // displayInfoWindow() {
    //   if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    //   }
    //   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
    //   this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    //   });
    //   this.map.setCenter(iwPosition);
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

.map-search {
  z-index: 999;
  left: 50%;
  top: 25px;
  position: absolute;
  transform: translate(-50%, -50%);
  height: 0;
}

.map-wrap {
  position: relative;
}
</style>
