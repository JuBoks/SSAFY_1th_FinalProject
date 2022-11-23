import {
  apiGetAptInfo,
  apiGetAptDealAvg,
  apiGetAptDealGroup,
  apiGetAptDealInfo,
  apiGetAptDealByMonth,
  apiRegistFavoriteArea,
  apiDeleteFavoriteArea,
  apiGetFavoriteAreaList,
} from "@/api/map";
import * as Kakao from "@/util/kakao.js";

const mapStore = {
  namespaced: true,
  state: {
    aptSelected: {}, // 선택한 아파트 객체
    aptSelectedAvg: "", // 선택한 아파트 최근 30일 동안 매매 평균 가격
    aptSelectedDealByMonth: [], // 선택한 아파트의 특정 년월의 매매 내역
    aptInfoList: [], // 좌측 리스트에 출력할 정보들
    aptDealInfo: [], // 한 아파트에 대한 거래 정보들
    aptFilterBtn: true, // 아파트 마커 보여줄 버튼 활성화 여부
    aptDealInfoGroup: [], // 한 아파트에 대한 년월 기준으로 평균 매매값
    aptDealInfoGroupChart: [["년도", "매매평균값"]], // 한 아파트에 대한 거래 정보들 (차트용)
    bookmark: false, // 관심지역 추가 체크박스
    favoriteAreas: [], // 관심지역 리스트
  },

  getters: {
    aptSelected(state) {
      return state.aptSelected;
    },
    aptSelectedAvg(state) {
      return state.aptSelectedAvg;
    },
    aptSelectedDealByMonth(state) {
      return state.aptSelectedDealByMonth;
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
    aptDealInfoGroup(state) {
      return state.aptDealInfoGroup;
    },
    aptDealInfoGroupChart(state) {
      return state.aptDealInfoGroupChart;
    },
    bookmark(state) {
      return state.bookmark;
    },
    favoriteAreas(state) {
      return state.favoriteAreas;
    },
  },
  mutations: {
    APT_SELECTED(state, payload) {
      state.aptSelected = payload.aptSelected;
    },
    APT_SELECTED_AVG(state, payload) {
      state.aptSelectedAvg = payload.aptSelectedAvg;
    },
    APT_SELECTED_BY_MONTH(state, payload) {
      state.aptSelectedDealByMonth = payload.aptSelectedDealByMonth;
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
    APT_DEAL_INFO_GROUP(state, payload) {
      state.aptDealInfoGroup = payload.aptDealInfoGroup;
    },
    APT_DEAL_INFO_GROUP_CHART(state, payload) {
      state.aptDealInfoGroupChart = payload.aptDealInfoGroupChart;
    },
    BOOKMARK(state, payload) {
      state.bookmark = payload.bookmark;
    },
    FAVORITE_AREAS(state, payload) {
      state.favoriteAreas = payload.favoriteAreas;
      }
  },
  actions: {
    setSelectedApt({ commit }, aptSelected) {
      commit({
        type: "APT_SELECTED",
        aptSelected,
      });
    },
    getSelectedAptDealAvg({ commit }, aptCode) {
      apiGetAptDealAvg(aptCode, ({ data }) => {
        commit({
          type: "APT_SELECTED_AVG",
          aptSelectedAvg: data,
        });
      });
    },
    getAptDealGroup({ commit }, aptCode) {
      apiGetAptDealGroup(aptCode, ({ data }) => {
        // chart 에 맞게 데이터 넣기
        let chartData = [["년도", "매매평균값"]];
        let length = data.length > 12 ? 12 : data.length;
        for (let i = 0; i < length; i++) {
          let el = data[i];
          chartData.push([
            `${el.dealYear}.${el.dealMonth}`,
            Number(el.dealAvg) / 10,
          ]);
        }
        commit({
          type: "APT_DEAL_INFO_GROUP",
          aptDealInfoGroup: data,
        });
        commit({
          type: "APT_DEAL_INFO_GROUP_CHART",
          aptDealInfoGroupChart: chartData,
        });
      });
    },
    getAptDealInfo({ commit }, aptCode) {
      apiGetAptDealInfo(aptCode, ({ data }) => {
        commit({
          type: "APT_DEAL_INFO",
          aptDealInfo: data,
        });
      });
    },
    getAptDealByMonth({ commit }, payload) {
      console;
      apiGetAptDealByMonth(payload, ({ data }) => {
        const filter = data.map((el) => {
          let amount = el.dealAmount.replace(",", "");
          amount = Number(amount);
          amount /= 10000;
          return {
            ...el,
            dealDate: `${el.dealYear}.${el.dealMonth}`,
            dealAmount: `${amount}억`,
          };
        });
        commit({
          type: "APT_SELECTED_BY_MONTH",
          aptSelectedDealByMonth: filter,
        });
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
    init({ commit }) {
      commit({
        type: "APT_SELECTED",
        aptSelected: {},
      });
      commit({
        type: "APT_SELECTED_BY_MONTH",
        aptSelectedDealByMonth: [],
      });
      commit({
        type: "APT_DEAL_INFO_GROUP_CHART",
        aptDealInfoGroupChart: [],
      });
    },
    searchArea({ dispatch }, payload) {
      // 1. 초기화
      dispatch("init");

      // 2. 아파트 정보 가져오기
      apiGetAptInfo(payload.dongCode, async ({ data }) => {
        const aptData = data;
          // 3. 좌측 리스트 정보 출력
        dispatch("updateAptInfoList", aptData);
        // 4. 맵에 마커 표시하기
        if (aptData.length > 0) {
          // 매물정보가 있으면 마커가 다 표시할 수 있도록 맵을 이동
          const aptMarkers = await Kakao.AptMarkers(aptData);
          payload.map.addAptCluster(aptMarkers);
        } else {
          // 매물정보가 없으면 임의로 이동
          const addr = `${payload.selectedSido.name} ${payload.selectedGugun.name} ${payload.selectedDong.name}`;
          payload.map.setCenterAddr(addr, 3);
        }
        // 5. callback
        payload.callback();
      });
    },
    /* 관심지역 */
    registFavoriteArea({commit}, payload) {
      apiRegistFavoriteArea(payload, ({ data }) => {
        commit({
          type: "FAVORITE_AREAS",
          favoriteAreas: data,
        });
      });
    },
    deleteFavoriteArea({ commit }, payload) {
      apiDeleteFavoriteArea(payload, ({ data }) => {
        commit({
          type: "FAVORITE_AREAS",
          favoriteAreas: data,
        });
      });
    },
    selectFavoriteArea({commit}, payload) {
      apiGetFavoriteAreaList(payload.userId, ({ data }) => {
        commit({
          type: "FAVORITE_AREAS",
          favoriteAreas: data,
        });
        payload.callback();
      });
    },
  },
};

export default mapStore;
