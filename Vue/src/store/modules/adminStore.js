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

const adminStore = {
  namespaced: true,
  state: {
    users: [],
    user: {},
    usersPerPage: 10, // 한 페이지의 글의 개수(안바뀜)
    totalUsers: 0, // 총 글의 개수
  },
  getters: {
    users(state) {
      return state.users;
    },
    user(state) {
      return state.user;
    },
    usersPerPage(state) {
      return state.usersPerPage;
    },
    totalUsers(state) {
      return state.totalUsers;
    },
  },
  mutations: {
    USERS(state, payload) {
      // state에 가공된 데이터 저장
      state.users = payload.users;
    },
    USER(state, payload) {
      state.user = payload.user;
    },
    USERS_COUNT(state, payload) {
      state.totalUsers = payload.count;
    },
  },
  actions: {
    getUsers(context, payload) {
      http
        .get(
          "/admin?pgno=" +
            payload.pgno +
            "&key=" +
            payload.key +
            "&word=" +
            payload.word
        )
        .then((response) => {
          switch (response.status) {
            case 200:
              context.commit({ type: "USERS", users: response.data });
              break;

            case 400:
              break;
            case 500:
              alertMessage(response.status);
              break;
          }
        });
    },
    createUser(context, payload) {
      http.post("/admin", payload.userInfo).then((response) => {
        switch (response.status) {
          case 200:
            alert("등록 되었습니다.");
            payload.callback();
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
    getUser(context, userId) {
      http.get(`/admin/${userId}`).then((response) => {
        switch (response.status) {
          case 200:
            //this.book = response.data;
            context.commit({
              type: "USER",
              user: response.data,
            });
            break;

          case 400:
          case 500:
            alertMessage(response.status);
            break;
        }
      });
    },
    getUserCount(context, payload) {
      const url = `/admin/count?key=${payload.key}&word=${payload.word}`;
      http.get(url).then((response) => {
        switch (response.status) {
          case 200:
            context.commit({
              type: "USERS_COUNT",
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
    modifyUser(context, payload) {
      http
        .put(`/admin/${payload.userInfo.userId}`, payload.userInfo)
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
    deleteUser(context, payload) {
      http.delete(`/admin/${payload.userId}`).then((response) => {
        switch (response.status) {
          case 200:
            alert("삭제가 완료되었습니다.");
            payload.callback();
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

export default adminStore;
