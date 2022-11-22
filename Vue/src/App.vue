<template>
  <div id="app">
    <the-header></the-header>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader.vue";

export default {
  components: {
    TheHeader,
  },
};
</script>

<style>
@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
* {
  font-famliy: "NanumSquare", san-serif;
  /* font-size: 17px; */
  line-height: 1.5;
  box-sizing: border-box;
}
#app {
  height: 100vh;
  width: 100%;
  position: absolute;
}
.app-container {
  width: 100%;
  height: calc(100% - 80px);
}
.app-nav {
  height: 80px;
}
.wh100 {
  width: 100%;
  height: 100%;
}
div[id^="google-visualization-errors"] {
  display: none !important;
}
</style>
