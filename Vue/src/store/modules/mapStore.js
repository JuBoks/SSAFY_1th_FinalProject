import {
  apiGetSido,
  apiGetGugun,
  apiGetDong,
  apiGetAptInfo,
  apiGetAptDealAvg,
  apiGetAptDealGroup,
  apiGetAptDealInfo,
  apiGetAptDealCancelInfo,
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
    aptDealCancelInfo: [], // 한 아파트에 대한 해제거래 정보들
    aptFilterBtn: true, // 아파트 마커 보여줄 버튼 활성화 여부
    aptDealInfoGroup: [], // 한 아파트에 대한 년월 기준으로 평균 매매값
    aptDealInfoGroupChart: [["년도", "매매평균값", "매매해약"]], // 한 아파트에 대한 거래 정보들 (차트용)
    bookmark: false, // 관심지역 추가 체크박스
    favoriteAreas: [], // 관심지역 리스트

    sidoSelected: null,
    gugunSelected: null,
    dongSelected: null,
    sidoOptions: [{ text: "시/도", value: null }],
    gugunOptions: [{ text: "구/군", value: null }],
    dongOptions: [{ text: "동", value: null }],
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
    aptDealCancelInfo(state) {
      return state.aptDealCancelInfo;
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
    sidoSelected(state) {
      return state.sidoSelected;
    },
    gugunSelected(state) {
      return state.gugunSelected;
    },
    dongSelected(state) {
      return state.dongSelected;
    },
    sidoOptions(state) {
      return state.sidoOptions;
    },
    gugunOptions(state) {
      return state.gugunOptions;
    },
    dongOptions(state) {
      return state.dongOptions;
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
    APT_DEAL_CANCEL_INFO(state, payload) {
      state.aptDealCancelInfo = payload.aptDealCancelInfo;
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
    },
    SIDO_SELECTED(state, payload) {
      state.sidoSelected = payload.sidoSelected;
    },
    GUGUN_SELECTED(state, payload) {
      state.gugunSelected = payload.gugunSelected;
    },
    DONG_SELECTED(state, payload) {
      state.dongSelected = payload.dongSelected;
    },
    SIDO_OPTIONS(state, payload) {
      state.sidoOptions = payload.sidoOptions;
    },
    GUGUN_OPTIONS(state, payload) {
      state.gugunOptions = payload.gugunOptions;
    },
    DONG_OPTIONS(state, payload) {
      state.dongOptions = payload.dongOptions;
    },
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
      apiGetAptDealGroup(aptCode, (response) => {
        let aptDealData = response.data;
        apiGetAptDealCancelInfo(aptCode, ({ data }) => {
          let aptDealCancelData = data;
          // chart 에 맞게 데이터 넣기
          let chartData = [["년도", "매매평균값", "매매해약"]];
          let lenDeal = aptDealData.length > 12 ? 12 : aptDealData.length;
          let lenCancel =
            aptDealCancelData.length > 12 ? 12 : aptDealCancelData.length;
          // deal 관련 데이터를 먼저 넣음
          for (let k = 0; k < lenDeal; k++) {
            let deal = aptDealData[k];
            let flag = false;
            for (let i = 0; i < lenCancel; i++) {
              let cancel = aptDealCancelData[i];
              if (
                cancel.dealYear == deal.dealYear &&
                cancel.dealMonth == deal.dealMonth
              ) {
                chartData.push([
                  `${deal.dealYear}.${deal.dealMonth}`,
                  Number(deal.dealAvg) / 10,
                  Number(cancel.dealAvg) / 10,
                ]);
                flag = true;
                break;
              }
            }
            if (!flag) {
              chartData.push([
                `${deal.dealYear}.${deal.dealMonth}`,
                Number(deal.dealAvg) / 10,
                0,
              ]);
            }
          }
          commit({
            type: "APT_DEAL_INFO_GROUP",
            aptDealInfoGroup: aptDealData,
          });
          commit({
            type: "APT_DEAL_INFO_GROUP_CHART",
            aptDealInfoGroupChart: aptDealCancelData,
          });
          commit({
            type: "APT_DEAL_INFO_GROUP_CHART",
            aptDealInfoGroupChart: chartData,
          });
        });
      });
      // let aptDealData = await apiGetAptDealGroup(aptCode);
      // let aptDealCancelData = await apiGetAptDealCancelInfo(aptCode);

      // for (let i = 0; i < lenDeal; i++) {
      //   let el = aptDealData[i];
      //   chartData.push([
      //     `${el.dealYear}.${el.dealMonth}`,
      //     Number(el.dealAvg) / 10,
      //   ]);
      // }
      // // 해제 관련 데이터를 맞춰 넣음
      // for (let i = 0; i < lenCancel; i++) {
      //   let el = aptDealCancelData[i];
      //   for (let k = 0; k < chartData.length; k++) {}
      // }

      // apiGetAptDealGroup(aptCode, ({ data }) => {
      //   // chart 에 맞게 데이터 넣기
      //   let chartData = [["년도", "매매평균값"]];
      //   let length = data.length > 12 ? 12 : data.length;
      //   for (let i = 0; i < length; i++) {
      //     let el = data[i];
      //     chartData.push([
      //       `${el.dealYear}.${el.dealMonth}`,
      //       Number(el.dealAvg) / 10,
      //     ]);
      //   }
      //   commit({
      //     type: "APT_DEAL_INFO_GROUP",
      //     aptDealInfoGroup: data,
      //   });
      //   commit({
      //     type: "APT_DEAL_INFO_GROUP_CHART",
      //     aptDealInfoGroupChart: chartData,
      //   });
      // });
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
    setBookmark({ commit }, flag) {
      commit({
        type: "BOOKMARK",
        bookmark: flag,
      });
    },
    registFavoriteArea({ commit }, payload) {
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
    selectFavoriteArea({ commit }, payload) {
      apiGetFavoriteAreaList(payload.userId, ({ data }) => {
        commit({
          type: "FAVORITE_AREAS",
          favoriteAreas: data,
        });
        payload.callback && payload.callback();
      });
    },
    /* 시군구 동 */
    setSido({ commit }, payload) {
      commit({
        type: "SIDO_SELECTED",
        sidoSelected: payload,
      });
    },
    setGugun({ commit }, payload) {
      commit({
        type: "GUGUN_SELECTED",
        gugunSelected: payload,
      });
    },
    setDong({ commit }, payload) {
      commit({
        type: "DONG_SELECTED",
        dongSelected: payload,
      });
    },
    setSidoOptions({ commit }, sidoOptions) {
      commit({
        type: "SIDO_OPTIONS",
        sidoOptions: sidoOptions,
      });
    },
    getSidoOptions({ dispatch }) {
      apiGetSido(({ data }) => {
        let sidoOptions = [{ text: "시/도", value: null }];
        data.forEach((el) => {
          sidoOptions.push({
            text: el.sidoName,
            value: {
              code: el.dongCode,
              name: el.sidoName,
            },
          });
        });
        dispatch("setSidoOptions", sidoOptions);
      });
    },
    setGugunOptions({ commit }, gugunOptions) {
      commit({
        type: "GUGUN_OPTIONS",
        gugunOptions: gugunOptions,
      });
    },
    async getGugunOptions({ dispatch }, sidoCode) {
      apiGetGugun(sidoCode, ({ data }) => {
        let gugunOptions = [{ text: "구/군", value: null }];
        data.forEach((el) => {
          gugunOptions.push({
            text: el.gugunName,
            value: {
              code: el.dongCode,
              name: el.gugunName,
            },
          });
        });
        dispatch("setGugunOptions", gugunOptions);
      });
    },
    setDongOptions({ commit }, dongOptions) {
      commit({
        type: "DONG_OPTIONS",
        dongOptions: dongOptions,
      });
    },
    getDongOptions({ dispatch }, gugunCode) {
      apiGetDong(gugunCode, ({ data }) => {
        let dongOptions = [{ text: "동", value: null }];
        data.forEach((el) => {
          dongOptions.push({
            text: el.dongName,
            value: {
              code: el.dongCode,
              name: el.dongName,
            },
          });
        });
        dispatch("setDongOptions", dongOptions);
      });
    },
  },
};

export default mapStore;
