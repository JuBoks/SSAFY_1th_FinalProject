<template>
  <div>
    <b-table
      v-if="aptSelectedDealByMonth"
      hover
      empty-text="거래 내역이 없습니다."
      :items="aptSelectedDealByMonth"
      :fields="fields"></b-table>
    <template v-else>
      <div class="text-center">조회할 일자를 선택해주세요.</div>
    </template>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
const mapStore = "mapStore";
export default {
  data() {
    return {
      fields: [
        {
          label: "계약일",
          key: "dealDate",
          sortable: true,
        },
        {
          label: "매매가격",
          key: "dealAmount",
          sortable: true,
        },
        {
          label: "면적(㎡)",
          key: "area",
          sortable: true,
        },
        {
          label: "층",
          key: "floor",
          sortable: true,
        },
      ],
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptSelectedDealByMonth"]),
  },
};
</script>

<style></style>
