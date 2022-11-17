import http from "@/util/http-common";

const userStore = {
  namespaced: true,
  state: {
    loginUser: null,
  },
  getters: {
    loginUser(state) {
      return state.loginUser;
    },
  },
  mutations: {
    LOG_IN(state, payload) {
      state.loginUser = payload.loginUser;
    },
    LOG_OUT(state) {
      state.loginUser = null;
    },
  },
  actions: {
    async login({ commit }, payload) {
      const loginUser = await http.post(`/user/login`, payload.input);
      commit({
        type: "LOG_IN",
        loginUser,
      });
      payload.callback();
    },
    async logout({ commit }, callback) {
      await http.get(`/user/logout`);
      commit({
        type: "LOG_OUT",
      });
      callback();
    },
  },
};

export default userStore;
