import axios from 'axios'
import { TEST_NET, MAIN_NET } from './consts'
const network = window.location.href.indexOf("testnet")>-1 ? 'TEST_NET' : MAIN_NET || sessionStorage.getItem('network')
// 创建axios实例
const service = axios.create({
  baseURL: network === TEST_NET ? process.env.TEST_API_URL : process.env.API_URL,
  timeout: 30000 // 请求超时时间
});

// request拦截器
service.interceptors.request.use(
  config => {

    // config.headers["Content-Type"] = "application/tstid.platform.api.v1+json";
    // config.headers["Accept"] = "application/tstid.platform.api.v1+json";
    // if (config.invoke_token) {
    //   config.headers['invoke_token'] = config.invoke_token;
    // }

    return config;
  },
  error => {
    console.log('e')
    Promise.reject(error);
  }
);

// respone拦截器
service.interceptors.response.use(
  response => {
    const data = response.data;
    return data;
  },
  error => {
    const response = error.response;
    return Promise.reject((response && response.data) || error);
  }
);

export default service;