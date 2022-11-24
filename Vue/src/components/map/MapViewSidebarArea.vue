<template>
  <b-list-group>
    <template v-if="favoriteAreas.length == 0">
      <div class="text-center">관심지역을 추가해주세요.</div>
    </template>
    <b-list-group
      v-else
      class="row"
      horizontal
      v-for="(el, idx) in favoriteAreas"
      :key="idx">
      <b-list-group-item
        class="col-9 text-center"
        button
        @click="showArea(el)"
        >{{ el.addr }}</b-list-group-item
      >
      <b-list-group-item class="col-3" button @click="deleteArea(el.dongCode)"
        >삭제</b-list-group-item
      >
    </b-list-group>
  </b-list-group>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";
const userStore = "userStore";

export default {
  computed: {
    ...mapGetters(mapStore, ["favoriteAreas", "dongSelected"]),
    ...mapGetters(userStore, ["loginUser"]),
  },
  methods: {
    ...mapActions(mapStore, [
      "deleteFavoriteArea",
      "searchArea",
      "setBookmark",
    ]),

    showArea(el) {
      let dongCode = el.dongCode;
      const payload = {
        dongCode: dongCode,
        map: window.mapInstance,
        selectedSido: dongCode.substring(0, 2),
        selectedGugun: dongCode.substring(0, 5),
        selectedDong: dongCode,
        callback: () => {
          this.$route.name != "MapList" &&
            this.$router.push({ name: "MapList" });
        },
      };

      this.searchArea(payload);
    },
    deleteArea(code) {
      this.deleteFavoriteArea({
        userId: this.loginUser.userId,
        dongCode: code,
      });
      if (this.dongSelected && this.dongSelected.code == code) {
        console.log("현재 선택한 동", this.dongSelected);
        this.setBookmark(false);
      }
    },
  },
};
</script>

<style></style>
