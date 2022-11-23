import { apiInstance } from "@/util/http-common";

const api = apiInstance();
async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function findPwd(userid, success, fail) {
  await api.get(`/user/findPwd/${userid}`).then(success).catch(fail);
}

async function checkTempNum(userid, tmpNum, success, fail) {
  await api
    .get(`/user/tempNum/${userid}?tmpNum=${tmpNum}`)
    .then(success)
    .catch(fail);
}

async function modifyPwd(userid, userPwd, success, fail) {
  await api
    .put(`/user/modifyPwd/${userid}`, { userPwd })
    .then(success)
    .catch(fail);
}

export {
  login,
  findById,
  tokenRegeneration,
  logout,
  findPwd,
  checkTempNum,
  modifyPwd,
};
