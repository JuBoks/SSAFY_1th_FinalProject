<template>
  <b-navbar class="map-nav" toggleable="md" variant="light">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav class="mr-auto">
        <b-nav-item>
          <b-button-group>
            <div
              class="badge badge-primary text-wrap text-center"
              style="
                width: 3em;
                font-size: 18px;
                line-height: 30px;
                border-radius: 0px;
                font-weight: 500;
                border-top-left-radius: 0.3rem;
                border-bottom-left-radius: 0.3rem;
              ">
              매물
            </div>
            <b-button
              variant="outline-primary"
              :pressed.sync="aptToggle"
              id="filter-apt">
              아파트</b-button
            >
          </b-button-group>
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav>
        <b-nav-item>
          <b-button-group>
            <div
              class="badge badge-primary text-wrap text-center"
              style="
                width: 6rem;
                font-size: 18px;
                line-height: 30px;
                border-radius: 0;
                font-weight: 500;
                border-top-left-radius: 0.3rem;
                border-bottom-left-radius: 0.3rem;
              ">
              주변정보
            </div>
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleMart"
              @click="onClickMart"
              id="filter-test">
              마트</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleHospital"
              @click="onClickHospital">
              병원</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleConvStore"
              @click="onClickConvStore">
              편의점</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleSchool"
              @click="onClickSchool">
              학교</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleParkingLot"
              @click="onClickParkingLot">
              주차장</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleGasStation"
              @click="onClickGasStation">
              주유소</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleSubway"
              @click="onClickSubway">
              지하철역</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="toggleBank"
              @click="onClickBank">
              은행</b-button
            >
            <b-button
              variant="outline-primary"
              :pressed.sync="togglePharmacy"
              @click="onClickPharmacy">
              약국</b-button
            >
          </b-button-group>
        </b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-item>
          <b-button variant="outline-primary" @click="showFavoriteAreaList">
            관심지역 보기</b-button
          >
        </b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import {
  Service,
  Mart,
  Hospital,
  ConvStore,
  School,
  ParkingLot,
  GasStation,
  Subway,
  Bank,
  Pharmacy,
} from "@/util/kakao-service";

const mapStore = "mapStore";

export default {
  data() {
    return {
      service: null,

      toggleMart: false,
      toggleHospital: false,
      toggleConvStore: false,
      toggleSchool: false,
      toggleParkingLot: false,
      toggleGasStation: false,
      toggleSubway: false,
      toggleBank: false,
      togglePharmacy: false,

      markerMart: null,
      markerHospital: null,
      markerConvStore: null,
      markerSchool: null,
      markerParkingLot: null,
      markerGasStation: null,
      markerSubway: null,
      markerBank: null,
      markerPharmacy: null,
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptFilterBtn"]),
    aptToggle: {
      get() {
        return this.aptFilterBtn;
      },
      set(flag) {
        this.toggleAptFilter(flag);
      },
    },
  },
  methods: {
    ...mapActions(mapStore, ["toggleAptFilter", "init"]),

    showFavoriteAreaList() {
      this.init();
      this.$emit("onPanelOpen");
      this.$router.push({ name: "MapArea" }).catch(() => {});
    },

    getServiceInstance() {
      if (this.service == null) {
        this.service = new Service();
      }
    },
    onClickCategory(type, toggle, marker) {
      this.getServiceInstance();
      if (marker == null) {
        switch (type) {
          case "Mart":
            this.markerMart = new Mart();
            marker = this.markerMart;
            break;
          case "Hospital":
            this.markerHospital = new Hospital();
            marker = this.markerHospital;
            break;
          case "ConvStore":
            this.markerConvStore = new ConvStore();
            marker = this.markerConvStore;
            break;
          case "School":
            this.markerSchool = new School();
            marker = this.markerSchool;
            break;
          case "ParkingLot":
            this.markerParkingLot = new ParkingLot();
            marker = this.markerParkingLot;
            break;
          case "GasStation":
            this.markerGasStation = new GasStation();
            marker = this.markerGasStation;
            break;
          case "Subway":
            this.markerSubway = new Subway();
            marker = this.markerSubway;
            break;
          case "Bank":
            this.markerBank = new Bank();
            marker = this.markerBank;
            break;
          case "Pharmacy":
            this.markerPharmacy = new Pharmacy();
            marker = this.markerPharmacy;
            break;
        }
      }
      if (toggle) {
        this.service.searchPlaces(marker);
      } else {
        this.service.removeMarker(marker);
      }
    },

    onClickMart() {
      this.toggleMart != this.toggleMart;
      this.onClickCategory("Mart", this.toggleMart, this.markerMart);
    },
    onClickHospital() {
      this.toggleHospital != this.toggleHospital;
      this.onClickCategory(
        "Hospital",
        this.toggleHospital,
        this.markerHospital
      );
    },
    onClickConvStore() {
      this.toggleConvStore != this.toggleConvStore;
      this.onClickCategory(
        "ConvStore",
        this.toggleConvStore,
        this.markerConvStore
      );
    },
    onClickSchool() {
      this.toggleSchool != this.toggleSchool;
      this.onClickCategory("School", this.toggleSchool, this.markerSchool);
    },
    onClickParkingLot() {
      this.toggleParkingLot != this.toggleParkingLot;
      this.onClickCategory(
        "ParkingLot",
        this.toggleParkingLot,
        this.markerParkingLot
      );
    },
    onClickGasStation() {
      this.toggleGasStation != this.toggleGasStation;
      this.onClickCategory(
        "GasStation",
        this.toggleGasStation,
        this.markerGasStation
      );
    },
    onClickSubway() {
      this.toggleSubway != this.toggleSubway;
      this.onClickCategory("Subway", this.toggleSubway, this.markerSubway);
    },
    onClickBank() {
      this.toggleBank != this.toggleBank;
      this.onClickCategory("Bank", this.toggleBank, this.markerBank);
    },
    onClickPharmacy() {
      this.togglePharmacy != this.togglePharmacy;
      this.onClickCategory(
        "Pharmacy",
        this.togglePharmacy,
        this.markerPharmacy
      );
    },
  },
};
</script>

<style scoped>
button {
  color: black;
  font-weight: bold;
}
/* #filter-apt::before {
  content: url("@/assets/img/apartment_b.png");
}
#filter-alliance::before {
  content: url("@/assets/img/alliance_b.png");
} */

.dateSel {
  margin-right: 1rem;
}
.navbar-expand-md .navbar-nav .nav-link {
  padding-right: 0;
}
.map-nav {
  height: 50px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.08);
}
</style>
