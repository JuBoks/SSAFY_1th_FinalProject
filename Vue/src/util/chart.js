export const chartType = "LineChart";

export const chartData = [
  ["Year", "Sales", "Expenses"],
  ["2004", 1000, 400],
  ["2005", 1170, 460],
  ["2006", 660, 1120],
  ["2007", 1030, 540],
];

export const chartOptions = {
  curveType: "function",
  legend: { position: "bottom" },
  width: 450,
  height: 300,
  crosshair: {
    trigger: "both",
    orientation: "vertical",
  },
};

export const chartEvents = {
  select: (el1, el2, el3) => {
    console.log(el1, el2, el3);
  },
};
