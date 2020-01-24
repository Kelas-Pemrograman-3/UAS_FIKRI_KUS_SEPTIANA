import axios from 'axios'

const axiosInstance = axios.create({
  // baseURL: 'http://192.168.43.246:5000'
  baseURL: 'http://192.168.0.108:5000'
})

export default async ({ Vue }) => {
  Vue.prototype.$axios = axiosInstance
}

export { axiosInstance }
