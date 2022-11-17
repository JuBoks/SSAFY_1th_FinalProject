<template>
  <b-navbar class="map-nav" toggleable="md" variant="light">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <!-- <b-navbar-nav class="dateSel">
      <b-nav-item>
        <b-form-select
          id="dealYear"
          class="mb-2 mr-sm-2 mb-sm-0"
          :options="year"
          v-model="ys"
          :value="ysVal"
          @change="onYearSelectChanged"
        ></b-form-select>
      </b-nav-item>
      <b-nav-item>
        <b-form-select
          id="dealMonth"
          class="mb-2 mr-sm-2 mb-sm-0"
          :options="month"
          v-model="ms"
          :value="msVal"
          @change="onMonthSelectChanged"
        ></b-form-select>
      </b-nav-item>
    </b-navbar-nav> -->

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
        <!-- <b-nav-item>
          <b-button
            pill
            variant="outline-primary"
            :pressed.sync="allianceToggle"
            id="filter-alliance"
          >
            연립다세대</b-button
          >
        </b-nav-item> -->
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
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
  mounted() {},
  methods: {
    ...mapActions(mapStore, ["toggleAptFilter"]),
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
