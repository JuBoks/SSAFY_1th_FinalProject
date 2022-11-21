<template>
  <GChart :type="type" :data="data" :options="options" :events="events" />
</template>

<script>
import { GChart } from "vue-google-charts/legacy";
import { mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  name: "GoogleChart",
  components: {
    GChart,
  },
  data() {
    return {
      type: "LineChart",
      data: this.aptDealInfoGroup,
      options: {
        curveType: "function",
        legend: { position: "bottom" },
        width: 400,
        height: 250,
        crosshair: {
          trigger: "both",
          orientation: "vertical",
        },
      },
      events: {
        select: (el1, el2, el3) => {
          console.log(el1, el2, el3);
        },
      },
    };
  },
  computed: {
    ...mapGetters(mapStore, ["aptDealInfoGroup"]),
  },
  created() {
    this.data = this.aptDealInfoGroup;
  },
  methods: {
    selectRow(el1, el2, el3) {
      console.log("select row", el1, el2, el3);
    },
  },
};
</script>
