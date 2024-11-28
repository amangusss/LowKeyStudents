import React from 'react'
import { Link, useHistory } from 'react-router-dom'
import './Navbar.css'

const Navbar: React.FC = () => {
  const history = useHistory()
  const isAuthenticated = !!localStorage.getItem('token')

  const handleLogout = () => {
    localStorage.removeItem('token')
    history.push('/login')
  }

  return (
    <nav className="navbar">
      <h1 className="navbar-logo">Low-Key Students</h1>
      <ul className="navbar-links">
        <li>
          <Link to="/">Главная</Link>
        </li>
        {isAuthenticated ? (
          <>
            <li>
              <Link to="/dashboard">Панель управления</Link>
            </li>
            <li>
              <button onClick={handleLogout} className="logout-button">
                Выйти
              </button>
            </li>
          </>
        ) : (
          <>
            <li>
              <Link to="/register">Регистрация</Link>
            </li>
            <li>
              <Link to="/login">Вход</Link>
            </li>
          </>
        )}
      </ul>
    </nav>
  )
}

export default Navbar
