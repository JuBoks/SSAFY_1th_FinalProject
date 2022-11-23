import { apiInstance } from "@/util/http-common";

const api = apiInstance();

async function removeUser(userId, success, fail) {
  await api.delete(`/admin/${userId}`).then(success).catch(fail);
}

export { removeUser };
