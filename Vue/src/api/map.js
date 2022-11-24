import { apiInstance } from "@/util/http-common";

const api = apiInstance();

// sido, gugun, dong 정보
async function apiGetSido(success, fail) {
  await api.get("/area/sido").then(success).catch(fail);
}
async function apiGetGugun(sidoCode, success, fail) {
  await api.get(`/area/gugun?sidoCode=${sidoCode}`).then(success).catch(fail);
}
async function apiGetDong(gugunCode, success, fail) {
  await api.get(`/area/dong?gugunCode=${gugunCode}`).then(success).catch(fail);
}
// 아파트 정보
async function apiGetAptInfo(dongCode, success, fail) {
  await api.get(`/aptinfo/${dongCode}`).then(success).catch(fail);
}
// 아파트 매매 정보
async function apiGetAptDealInfo(aptCode, success, fail) {
  await api.get(`/aptdeal/${aptCode}`).then(success).catch(fail);
}
async function apiGetAptDealCancelInfo(aptCode, success, fail) {
  await api.get(`/aptdeal/cancel/${aptCode}`).then(success).catch(fail);
}
async function apiGetAptDealAvg(aptCode, success, fail) {
  await api.get(`/aptdeal/avg/${aptCode}`).then(success).catch(fail);
}
async function apiGetAptDealGroup(aptCode, success, fail) {
  await api.get(`/aptdeal/group/${aptCode}`).then(success).catch(fail);
}
async function apiGetAptDealByMonth(payload, success, fail) {
  await api
    .get(
      `/aptdeal/month/${payload.aptCode}?dealYear=${payload.dealYear}&dealMonth=${payload.dealMonth}`
    )
    .then(success)
    .catch(fail);
}
// 관심지역
async function apiRegistFavoriteArea(payload, success, fail) {
  await api
    .post(`/favoritearea/${payload.userId}`, JSON.stringify(payload))
    .then(success)
    .catch(fail);
}
async function apiGetFavoriteAreaList(userId, success, fail) {
  await api.get(`/favoritearea/${userId}`).then(success).catch(fail);
}
async function apiDeleteFavoriteArea(payload, success, fail) {
  await api
    .delete(`/favoritearea/${payload.userId}/${payload.dongCode}`)
    .then(success)
    .catch(fail);
}

export {
  apiGetSido,
  apiGetGugun,
  apiGetDong,
  apiGetAptInfo,
  apiGetAptDealInfo,
  apiGetAptDealCancelInfo,
  apiGetAptDealAvg,
  apiGetAptDealGroup,
  apiGetAptDealByMonth,
  apiRegistFavoriteArea,
  apiGetFavoriteAreaList,
  apiDeleteFavoriteArea,
};
