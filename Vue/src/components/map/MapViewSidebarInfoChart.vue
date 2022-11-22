<template>
  <GChart
    class="gchart"
    ref="gchart"
    :type="type"
    :data="aptDealInfoGroupChart"
    :options="options"
    :events="events"
  />
</template>

<script>
import { GChart } from "vue-google-charts/legacy";
import { mapActions, mapGetters } from "vuex";

const mapStore = "mapStore";

export default {
  name: "GoogleChart",
  components: {
    GChart,
  },
  data() {
    return {
      type: "LineChart",
      options: {
        curveType: "function",
        legend: { position: "bottom" },
        width: 400,
        height: 250,
        crosshair: {
          trigger: "both",
          orientation: "vertical",
        },
        hAxis: {
          textStyle: {
            color: "black",
            fontSize: 8,
          },
          slantedText: true,
          slantedTextAngle: 50,
        },
        vAxis: { format: "#,###억" },
        tooltip: {
          textStyle: {
            color: "black",
            fontSize: 10,
          },
        },
      },
      events: {
        select: () => {
          const selectedRow =
            this.$refs.gchart.chartObject.getSelection()[0].row + 1;
          const [dealYear, dealMonth] =
            this.aptDealInfoGroupChart[selectedRow][0].split(".");
          const aptCode = this.aptSelected.aptCode;

          this.getAptDealByMonth({
            aptCode,
            dealYear,
            dealMonth,
          });
        },
      },
    };
  },
  computed: {
    ...mapGetters(mapStore, [
      "aptDealInfoGroup",
      "aptDealInfoGroupChart",
      "aptSelected",
      "aptSelectedDealByMonth",
    ]),
  },
  methods: {
    ...mapActions(mapStore, ["getAptDealByMonth"]),
  },
};
</script>
<style scoped>
</style>