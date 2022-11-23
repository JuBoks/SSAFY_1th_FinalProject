import http from "@/util/http-common";

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

const newsStore = {
  namespaced: true,
  state: {
    news: [],
  },
  getters: {
    news(state) {
      return state.news;
    },
  },
  mutations: {
    NEWS(state, payload) {
      // state에 가공된 데이터 저장
      state.news = payload.news;
    },
  },
  actions: {
    getAllNews(context) {
      console.log("시작", context);
      http.get("/news").then((response) => {
        switch (response.status) {
          case 200:
            context.commit({ type: "NEWS", news: response.data });
            break;

          case 400:
            break;
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
  },
};

export default newsStore;
