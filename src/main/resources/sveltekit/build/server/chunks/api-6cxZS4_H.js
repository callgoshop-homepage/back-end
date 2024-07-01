import axios from 'axios';

const accessToken = async () => {
  localStorage.getItem("accessToken");
  const storedRefreshToken = localStorage.getItem("refreshToken");
  if (!storedRefreshToken && window.location.pathname !== "/") {
    alert("로그인을 다시 진행해주세요.");
    window.location.href = "http://localhost:5173/";
    throw new Error("Refresh Token이 없어서 새로운 Access Token을 발급할 수 없습니다.");
  }
  try {
    const response = await axios.post("http://localhost:8080/api/v1/access-token", null, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${storedRefreshToken}`
      }
    });
    if (response.status === 200) {
      const data = response.data;
      const newAccessToken = data.data.newAccessToken;
      localStorage.setItem("accessToken", newAccessToken);
      return newAccessToken;
    } else {
      console.error("서버 응답 오류:", response.statusText);
      throw new Error("Access Token 갱신 중 서버 응답 오류");
    }
  } catch (error) {
    if (error.response) {
      console.error("Response data:", error.response.data);
      console.error("Response status:", error.response.status);
      console.error("Response headers:", error.response.headers);
      if (error.response.status === 401 || error.response.status === 403 || error.response.status === 500 && window.location.pathname !== "/") {
        alert("로그인이 만료되었습니다. 다시 로그인 해주세요.");
        window.location.href = "http://localhost:5173/";
      }
    } else if (error.request) {
      console.error("Request data:", error.request);
    } else {
      console.error("Error message:", error.message);
    }
    console.error("Error config:", error.config);
    throw new Error("Access Token 갱신 중 오류 발생");
  }
};
const instance = axios.create({
  baseURL: "http://localhost:8080/api/v1/",
  timeout: 1e3
});
instance.interceptors.request.use(
  async (config) => {
    if (typeof window !== "undefined") {
      const refreshToken = localStorage.getItem("refreshToken");
      const accessToken2 = localStorage.getItem("accessToken");
      if (!refreshToken) {
        return config;
      }
      if (accessToken2) {
        config.headers["Content-Type"] = "application/json";
        config.headers["Authorization"] = `Bearer ${accessToken2}`;
      } else {
        console.error("No access token found");
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
instance.interceptors.response.use(
  async (response) => {
    if (response.status === 200) {
      response = response.data;
      const username = response.data.username;
      return username;
    }
    if (response.status === 404) ;
    return response;
  },
  async (error) => {
    if (typeof window !== "undefined") {
      if (error.response?.status === 401 || error.response?.status === 500) {
        const newAccessToken = await accessToken();
        window.location.reload();
        if (newAccessToken) {
          localStorage.setItem("accessToken", newAccessToken);
          error.config.headers["Authorization"] = `Bearer ${newAccessToken}`;
          return axios.request(error.config);
        }
      }
    }
    return Promise.reject(error);
  }
);

export { instance as i };
//# sourceMappingURL=api-6cxZS4_H.js.map
