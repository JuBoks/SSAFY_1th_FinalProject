import { apiInstance } from "@/util/http-common";

const api = apiInstance();

async function apiGetAptDealInfo(aptCode, success, fail) {
  await api.get(`/aptdeal/${aptCode}`).then(success).catch(fail);
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

export {
  apiGetAptDealInfo,
  apiGetAptDealAvg,
  apiGetAptDealGroup,
  apiGetAptDealByMonth,
};
