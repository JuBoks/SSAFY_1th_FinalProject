<template>
  <GChart
    class="gchart"
    ref="gchart"
    :type="type"
    :data="data"
    :options="options"
    :events="events" />
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
      data: this.aptDealInfoGroupChart,
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
          const [dealYear, dealMonth] = this.data[selectedRow][0].split(".");
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
  mounted() {
    let filter = [];
    for (let i = 0; i < 13; i++) {
      if (this.aptDealInfoGroupChart[i]) {
        filter.push(this.aptDealInfoGroupChart[i]);
      } else {
        break;
      }
    }
    this.data = filter;
  },
  methods: {
    ...mapActions(mapStore, ["getAptDealByMonth"]),
  },
};
</script>
