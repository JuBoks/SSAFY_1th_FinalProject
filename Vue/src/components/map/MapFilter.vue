<template>
  <b-navbar class="map-nav" toggleable="md" variant="light">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item>
          <b-button
            pill
            variant="outline-primary"
            :pressed.sync="aptToggle"
            id="filter-apt">
            아파트</b-button
          >
        </b-nav-item>
        <b-nav-item>
          <b-button
            pill
            variant="outline-primary"
            :pressed.sync="testToggle"
            @click="onClickTest"
            id="filter-test">
            마커테스트</b-button
          >
        </b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import { Service } from "@/util/kakao-service";

const mapStore = "mapStore";

export default {
  data() {
    return {
      testToggle: false,
      service: null,
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
    ...mapActions(mapStore, ["toggleAptFilter"]),
    onClickTest() {
      if (this.service == null) {
        this.service = new Service();
      }

      this.testToggle != this.testToggle;
      if (this.testToggle) {
        this.service.searchPlaces("CS2");
      } else {
        this.service.removeMarker();
      }
    },
  },
};
</script>

<style scoped>
button {
  color: black;
  font-weight: bold;
}
#filter-apt::before {
  content: url("@/assets/img/apartment_b.png");
}
#filter-alliance::before {
  content: url("@/assets/img/alliance_b.png");
}

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
