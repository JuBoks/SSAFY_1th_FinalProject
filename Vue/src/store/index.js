import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common";
import persistedState from "vuex-persistedstate";
import moment from "moment";
// module import
import userStore from "@/store/modules/userStore";

Vue.use(Vuex);

function alertMessage(statusCode) {
  switch (statusCode) {
    case 400:
      alert("잘못된 요청입니다.");
      break;

    case 500:
      alert("서버 오류!!!");
      break;
  }
}

export default new Vuex.Store({
  modules: {
    userStore,
  },
  state: {
    // User 시작

    // User 끝
    // Board 시작
    articles: [],
    article: {},
    articlesPerPage: 10, // 한 페이지의 글의 개수(안바뀜)
    totalArticles: 0, // 총 글의 개수
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
    articles(state) {
      return state.articles;
    },
    article(state) {
      return state.article;
    },
    articlesPerPage(state) {
      return state.articlesPerPage;
    },
    totalArticles(state) {
      return state.totalArticles;
    },
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
    ARTICLES(state, payload) {
      // state에 가공된 데이터 저장
      payload.articles.map((article) => {
        const obj = moment(article.registerTime, "YYYY-MM-DD hh:mm:ss");
        article.registerTime = obj.format("YYYY-MM-DD HH:mm");
        return article;
      });
      state.articles = payload.articles;
    },
    ARTICLE(state, payload) {
      state.article = payload.article;
    },
    ARTICLE_COUNT(state, payload) {
      state.totalArticles = payload.count;
    },
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
    getArticles(context, payload) {
      http
        .get(
          "/board?pgno=" +
            payload.pgno +
            "&key=" +
            payload.key +
            "&word=" +
            payload.word
        )
        .then((response) => {
          switch (response.status) {
            case 200:
              context.commit({ type: "ARTICLES", articles: response.data });
              break;

            case 400:
              break;
            case 500:
              alertMessage(response.status);
              break;
          }
        });
    },
    createArticle(context, payload) {
      http.post("/board", payload.article).then((response) => {
        switch (response.status) {
          case 200:
            alert("등록 되었습니다.");
            //this.moveList();
            payload.callback();
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
    getArticle(context, articleNo) {
      http.get(`/board/${articleNo}`).then((response) => {
        switch (response.status) {
          case 200:
            //this.book = response.data;
            context.commit({
              type: "ARTICLE",
              article: response.data,
            });
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
    getArticleCount(context, payload) {
      const url = `/board/count?key=${payload.key}&word=${payload.word}`;
      http.get(url).then((response) => {
        switch (response.status) {
          case 200:
            context.commit({
              type: "ARTICLE_COUNT",
              count: response.data,
            });
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
    modifyArticle(context, payload) {
      http
        .put(`/board/${payload.article.articleNo}`, payload.article)
        .then((response) => {
          switch (response.status) {
            case 200:
              alert("수정이 완료되었습니다.");
              payload.callback();
              break;

            case 400:
            case 500:
              alertMessage(response.status);
              break;
          }
        });
    },
    deleteArticle(context, payload) {
      http.delete(`/board/${payload.articleNo}`).then((response) => {
        switch (response.status) {
          case 200:
            alert("삭제가 완료되었습니다.");
            //this.$router.push({ name: "BookList" });
            payload.callback();
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
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
