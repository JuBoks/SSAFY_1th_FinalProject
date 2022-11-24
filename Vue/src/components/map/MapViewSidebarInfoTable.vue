<template>
  <div>
    <b-table
      v-if="aptSelectedDealByMonth"
      hover
      show-empty
      :emptyText="empty"
      :items="aptSelectedDealByMonth"
      :fields="fields"
    >
      <template #empty="scope"> {{ scope.emptyText }} </template>
    </b-table>
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
      empty: "거래 일자를 선택해주세요",
      fields: [
        {
          label: "계약일",
          key: "dealDate",
          class: "text-center",
        },
        {
          label: "매매가격",
          key: "dealAmount",
          class: "text-center",
        },
        {
          label: "면적(㎡)",
          key: "area",
          class: "text-center",
        },
        {
          label: "층",
          key: "floor",
          class: "text-center",
        },
        {
          label: "계약상태",
          key: "isCancel",
          class: "text-center",
        },
      ],
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptSelectedDealByMonth"]),
  },
};
</script>

<style scoped>
</style>
