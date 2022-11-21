import http from "@/util/http-common";

const mapStore = {
  namespaced: true,
  state: {
    aptSelected: {}, // 선택한 아파트 객체
    aptInfoList: [], // 좌측 리스트에 출력할 정보들
    aptDealInfo: [], // 한 아파트에 대한 거래 정보들
    aptFilterBtn: true, // 아파트 마커 보여줄 버튼 활성화 여부
    panelOpen: false, // 좌측 리스트 panel 열림여부
  },
  getters: {
    aptSelected(state) {
      return state.aptSelected;
    },
    aptInfoList(state) {
      return state.aptInfoList;
    },
    aptDealInfo(state) {
      return state.aptDealInfo;
    },
    aptFilterBtn(state) {
      return state.aptFilterBtn;
    },
    panelOpen(state) {
      return state.panelOpen;
    },
  },
  mutations: {
    APT_SELECTED(state, payload) {
      state.aptSelected = payload.aptSelected;
    },
    APT_INFO_LIST(state, payload) {
      state.aptInfoList = payload.aptInfoList;
    },
    APT_DEAL_INFO(state, payload) {
      state.aptDealInfo = payload.aptDealInfo;
    },
    APT_FILTER_BTN(state, payload) {
      state.aptFilterBtn = payload.flag;
    },
    PANEL_OPEN(state, payload) {
      state.panelOpen = payload.flag;
    },
  },
  actions: {
    setAptSelected({ commit }, aptSelected) {
      commit({
        type: "APT_SELECTED",
        aptSelected,
      });
    },
    async getAptDealInfo({ commit }, aptCode) {
      const { data } = await http.get(`/aptdeal/${aptCode}`);
      commit({
        type: "APT_DEAL_INFO",
        aptDealInfo: data,
      });
    },
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
    setPanelOpen({ commit }, flag) {
      commit({
        type: "PANEL_OPEN",
        flag,
      });
    },
  },
};

export default mapStore;
