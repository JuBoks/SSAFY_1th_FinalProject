const mapStore = {
  namespaced: true,
  state: {
    aptInfoList: [], // 좌측 리스트에 출력할 정보들
    aptFilterBtn: true, // 아파트 마커 보여줄 버튼 활성화 여부
  },
  getters: {
    aptInfoList(state) {
      return state.aptInfoList;
    },
    aptFilterBtn(state) {
      return state.aptFilterBtn;
    },
  },
  mutations: {
    APT_INFO_LIST(state, payload) {
      state.aptInfoList = payload.aptInfoList;
    },
    APT_FILTER_BTN(state, payload) {
      state.aptFilterBtn = payload.flag;
    },
  },
  actions: {
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
  },
};

export default mapStore;
