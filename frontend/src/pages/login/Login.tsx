import React, { useState, useContext } from 'react';
import { login as loginApi } from '../../services/api';
import { useHistory } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import { toast } from 'react-toastify';
import { AxiosError } from 'axios';
import './Login.css';

const Login: React.FC = () => {
  const [form, setForm] = useState({ email: '', password: '' });
  const history = useHistory();
  const { login } = useContext(AuthContext);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await loginApi(form.email, form.password);
      toast.success('Вход выполнен успешно!');
      login(response.data.token);
      history.push('/dashboard');
    } catch (error: unknown) {
      if (error instanceof AxiosError) {
        toast.error(error.response?.data?.message || 'Ошибка входа');
      } else {
        toast.error('Произошла непредвиденная ошибка');
      }
    }
  };

  return (
    <div className="login-container">
      <h2>Вход</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <input
          type="email"
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Электронная почта"
          required
        />
        <input
          type="password"
          name="password"
          value={form.password}
          onChange={handleChange}
          placeholder="Пароль"
          required
        />
        <button type="submit">Войти</button>
      </form>
    </div>
  );
};

export default Login;
