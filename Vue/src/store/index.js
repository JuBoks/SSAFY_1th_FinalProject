import Vue from "vue";
import Vuex from "vuex";
import persistedState from "vuex-persistedstate";

// module import
import userStore from "@/store/modules/userStore";
import boardStore from "@/store/modules/boardStore";
import mapStore from "@/store/modules/mapStore";
import adminStore from "@/store/modules/adminStore";
import noticeStore from "@/store/modules/noticeStore";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    boardStore,
    mapStore,
    adminStore,
    noticeStore,
  },
  // vuex-persistedstate 를 사용하기 위해 plugins 추가
  plugins: [
    persistedState({
      // 기본값은 localStorage
      storage: sessionStorage,
    }),
  ],
});
