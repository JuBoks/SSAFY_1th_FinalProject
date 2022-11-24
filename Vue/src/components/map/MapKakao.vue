<template>
  <div class="map-wrap wh100">
    <b-form inline class="map-search">
      <b-form-checkbox
        v-model="check.checked"
        ref="test"
        @change="onChangeBookmark"
        size="lg"></b-form-checkbox>
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedSido"
        value-field="value"
        text-field="text"
        :options="sidoOptions"
        :text="selectedSidoName"
        @change="onSidoChanged"></b-form-select>
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedGugun"
        value-field="value"
        text-field="text"
        :options="gugunOptions"
        :value="null"
        @change="onGugunChanged"></b-form-select>
      <b-form-select
        class="mb-2 mr-sm-2 mb-sm-0"
        v-model="selectedDong"
        value-field="value"
        text-field="text"
        :options="dongOptions"
        :value="null"
        @change="onDongChanged"></b-form-select>
    </b-form>

    <div id="map" style="width: 100%; height: 100%"></div>
  </div>
</template>

<script>
import * as http from "@/util/http-common";
import * as Kakao from "@/util/kakao.js";
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";
const userStore = "userStore";

export default {
  props: {
    yearSelected: Number,
    monthSelected: Number,
  },
  name: "KakaoMap",
  data() {
    return {
      selectedSidoName: null,

      selectedSido: null,
      selectedGugun: null,
      selectedDong: null,
    };
  },
  computed: {
    ...mapGetters(mapStore, [
      "bookmark",
      "favoriteAreas",
      "sidoSelected",
      "gugunSelected",
      "dongSelected",
      "sidoOptions",
      "gugunOptions",
      "dongOptions",
    ]),
    ...mapGetters(userStore, ["loginUser"]),
    check() {
      return { checked: this.bookmark };
    },
  },
  async mounted() {
    // 시/도 초기화
    this.getSidoOptions();
    this.setGugunOptions([{ text: "구/군", value: null }]);
    this.setDongOptions([{ text: "동", value: null }]);

    // 선택된 부분 초기화
    this.setSido(null);
    this.setGugun(null);
    this.setDong(null);

    this.selectedSido = this.sidoSelected;
    this.selectedGugun = this.gugunSelected;
    this.selectedDong = this.dongSelected;

    // 카카오 맵 생성
    this.map = new Kakao.Map();
    window.mapInstance = this.map;
  },
  methods: {
    ...mapActions(mapStore, [
      "updateAptInfoList",
      "init",
      "registFavoriteArea",
      "deleteFavoriteArea",
      "selectFavoriteArea",
      "searchArea",
      "setSido",
      "setGugun",
      "setDong",
      "getSidoOptions",
      "getGugunOptions",
      "getDongOptions",
      "setSidoOptions",
      "setGugunOptions",
      "setDongOptions",
      "setBookmark",
    ]),
    onChangeBookmark(checked) {
      let isValid = true;
      let errMsg = "";

      !this.loginUser
        ? ((errMsg = "로그인이 필요한 서비스입니다."), (isValid = false))
        : !this.selectedDong
        ? ((errMsg = "동을 선택해주세요."), (isValid = false))
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
        this.$refs.test.value = false;
        return;
      }

      const payload = {
        userId: this.loginUser.userId,
        dongCode: this.selectedDong.code,
      };

      this.setBookmark(checked);

      if (checked) {
        // 관심지역에 추가
        this.registFavoriteArea(payload);
      } else {
        // 관심지역 삭제
        this.deleteFavoriteArea(payload);
      }
    },

    async onSidoChanged() {
      // 1 구군, 동 초기화
      this.setGugun(null);
      this.setDong(null);
      this.setGugunOptions([{ text: "구/군", value: null }]);
      this.setDongOptions([{ text: "동", value: null }]);

      // 2. 값 셋팅
      this.setSido(this.selectedSido);
      this.getGugunOptions(this.selectedSido.code);
    },
    async onGugunChanged() {
      // 1. 동 초기화
      this.setDong(null);
      this.setDongOptions([{ text: "동", value: null }]);

      // 2. 동 셋팅
      this.setGugun(this.selectedGugun);
      this.getDongOptions(this.selectedGugun.code);
    },
    onDongChanged() {
      this.setDong(this.selectedDong);
      this.search();
      // 관심지역 추가 부분
      if (this.loginUser) {
        this.selectFavoriteArea({
          userId: this.loginUser.userId,
          callback: () => {
            for (let el of this.favoriteAreas) {
              if (el.dongCode == this.selectedDong.code) {
                this.check = true;
                break;
              }
            }
          },
        });
      }
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

      const payload = {
        dongCode: this.selectedDong.code,
        map: this.map,
        selectedSido: this.selectedSido,
        selectedGugun: this.selectedGugun,
        selectedDong: this.selectedDong,
        callback: () => {
          this.$route.name != "MapList" &&
            this.$router.push({ name: "MapList" });
          this.$parent.$emit("onPanelOpen");
        },
      };

      this.searchArea(payload);
    },
  },
};
</script>

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
