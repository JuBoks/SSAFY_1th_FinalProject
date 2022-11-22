<template>
  <div>
    <b-table
      v-if="aptInfoList && aptInfoList.length > 0"
      hover
      @row-hovered="onRowHovered"
      @row-unhovered="onRowUnhovered"
      @row-clicked="onRowClicked"
      :items="aptInfoList"
      :fields="fields"></b-table>
    <template v-else>
      <div class="text-center">검색 결과가 없습니다.</div>
    </template>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  data() {
    return {
      fields: [
        {
          label: "아파트명",
          key: "apartmentName",
          sortable: true,
        },
        {
          label: "건축년도",
          key: "buildYear",
          sortable: true,
        },
      ],
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptInfoList"]),
  },
  methods: {
    ...mapActions(mapStore, [
      "getAptDealGroup",
      "setSelectedApt",
      "getSelectedAptDealAvg",
    ]),
    onRowHovered(item) {
      item.position && window.map.panTo(item.position);
      item.onHover && item.onHover();
    },
    onRowUnhovered(item) {
      item.onHoverout && item.onHoverout();
    },
    async onRowClicked(item) {
      this.setSelectedApt(item);
      this.getSelectedAptDealAvg(item.aptCode);
      this.getAptDealGroup(item.aptCode);
      this.$router.push({ name: "MapInfo" });
    },
  },
};
</script>

<style>
.col-apt {
  width: 70%;
}
.col-build-year {
  width: 30%;
  text-align: center;
}
</style>
