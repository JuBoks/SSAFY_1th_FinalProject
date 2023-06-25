import axios from "axios";

const ERROR_MSG_SERVER = "서버 오류입니다.";
const http = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance };
export default http;

export async function get(url) {
  const { status, data } = await http.get(url);
  switch (status) {
    case 200:
      return data;
    case 500:
      alert(ERROR_MSG_SERVER);
      return null;
  }
}

export async function post(url, payload) {
  const { status, data } = await http.post(url, payload);
  switch (status) {
    case 200:
      return data;
    case 500:
      alert(ERROR_MSG_SERVER);
      return null;
  }
}

export async function put(url, payload) {
  const { status, data } = await http.put(url, payload);
  switch (status) {
    case 200:
      return data;
    case 500:
      alert(ERROR_MSG_SERVER);
      return null;
  }
}
