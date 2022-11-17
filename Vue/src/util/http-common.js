import axios from "axios";

const ERROR_MSG_SERVER = "서버 오류입니다.";
const http = axios.create({
  baseURL: "http://localhost:9999",
  headers: {
    "Content-Type": "application/json",
  },
});

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
