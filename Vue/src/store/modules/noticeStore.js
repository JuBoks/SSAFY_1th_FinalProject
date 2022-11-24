import http from "@/util/http-common";
import moment from "moment";

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

const noticeStore = {
  namespaced: true,
  state: {
    articles: [],
    article: {},
    articlesPerPage: 10, // 한 페이지의 글의 개수(안바뀜)
    totalArticles: 0, // 총 글의 개수
  },
  getters: {
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
  },
  mutations: {
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
    COMMENTS(state, payload) {
      payload.comments.map((comment) => {
        const obj = moment(comment.commentTime, "YYYY-MM-DD hh:mm:ss"); // 변경 전 양식으로 Moment 객체 생성
        comment.commentTime = obj.format("MM-DD HH:mm"); // 원하는 형식으로 변경 후 commentTime 갱신
        return comment;
      });
      state.comments = payload.comments;
    },
  },
  actions: {
    getArticles(context, payload) {
      http
        .get(
          "/notice?pgno=" +
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
      http.post("/notice", payload.article).then((response) => {
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
      http.get(`/notice/${articleNo}`).then((response) => {
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
      const url = `/notice/count?key=${payload.key}&word=${payload.word}`;
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
        .put(`/notice/${payload.article.articleNo}`, payload.article)
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
      http.delete(`/notice/${payload.articleNo}`).then((response) => {
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

    getComments(context, articleNo) {
      http.get(`/comment/${articleNo}`).then((response) => {
        switch (response.status) {
          case 200:
            context.commit({
              type: "COMMENTS",
              comments: response.data,
            });
            break;
          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
  },
};

export default noticeStore;
