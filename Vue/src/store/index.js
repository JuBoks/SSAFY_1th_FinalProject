import Vue from "vue";
import Vuex from "vuex";
import persistedState from "vuex-persistedstate";

// module import
import userStore from "@/store/modules/userStore";
import boardStore from "@/store/modules/boardStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userStore,
    boardStore,
  },
  state: {
    // User 시작

    // User 끝
    // Board 시작

    // Board 끝
    // Map 시작
    aptInfoList: [], // 좌측 리스트에 출력할 정보들
    aptFilterBtn: true, // 아파트 마커 보여줄 버튼 활성화 여부
    // Map 끝
  },
  getters: {
    // User 시작

    // User 끝
    // Board 시작

    // Board 끝
    // Map 시작
    aptInfoList(state) {
      return state.aptInfoList;
    },
    aptFilterBtn(state) {
      return state.aptFilterBtn;
    },
    // Map 끝
  },
  mutations: {
    // User 시작

    // User 끝
    // Board 시작

    // Board 끝
    // Map 시작
    APT_INFO_LIST(state, payload) {
      state.aptInfoList = payload.aptInfoList;
    },
    APT_FILTER_BTN(state, payload) {
      state.aptFilterBtn = payload.flag;
    },
    // Map 끝
  },
  actions: {
    // User 시작

    // User 끝
    // Board 시작

    // Board 끝
    // Map 시작
    updateAptInfoList({ commit }, aptInfoList) {
      commit({
        type: "APT_INFO_LIST",
        aptInfoList,
      });
    },
    toggleAptFilter({ commit }, flag) {
      // 클러스터 처리하기
      if (flag) {
        // 마커 보여주기
        window.mapInstance.showAptMarkers();
      } else {
        // 마커 숨기기
        window.mapInstance.hideAptMarkers();
      }
      commit({
        type: "APT_FILTER_BTN",
        flag,
      });
    },
    // Map 끝
  },
  // vuex-persistedstate 를 사용하기 위해 plugins 추가
  plugins: [
    persistedState({
      // 기본값은 localStorage
      storage: sessionStorage,
    }),
  ],
});
