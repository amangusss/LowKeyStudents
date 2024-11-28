import axios from 'axios'
import { toast } from 'react-toastify'

const API_URL = '/api' // Благодаря прокси

const instance = axios.create({
  baseURL: API_URL,
})

instance.interceptors.response.use(
  (response) => response,
  (error) => {
    const message = error.response?.data?.message || 'Произошла ошибка'
    toast.error(message)
    return Promise.reject(error)
  }
)

export const register = (name: string, email: string, password: string) => {
  return instance.post('/auth/register', { name, email, password })
}

export const login = (email: string, password: string) => {
  return instance.post('/auth/login', { email, password })
}

export const getUsers = (token: string) => {
  return instance.get('/users', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
}
