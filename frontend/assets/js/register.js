// register.js

document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('register-form');
    if (registerForm) {
      registerForm.addEventListener('submit', registerUser);
    }
  
    // Add Enter key functionality for registration form
    const registerUsername = document.getElementById('register-username');
    const registerPassword = document.getElementById('register-password');
    const registerEmail = document.getElementById('register-email');
    const registerPhone = document.getElementById('register-phone');
  
    registerUsername.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        registerUser(e);
      }
    });
  
    registerPassword.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        registerUser(e);
      }
    });
  
    registerEmail.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        registerUser(e);
      }
    });
  
    registerPhone.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        registerUser(e);
      }
    });
  });
  
  async function registerUser(event) {
    event.preventDefault();
    const username = document.getElementById('register-username').value.trim();
    const password = document.getElementById('register-password').value.trim();
    const email = document.getElementById('register-email').value.trim();
    const phoneNumber = document.getElementById('register-phone').value.trim();
  
    if (username === '' || password === '' || email === '' || phoneNumber === '') {
      showNotification('Please fill in all fields.', 'error');
      return;
    }
  
    const newUser = {
      username: username,
      password: password,
      email: email,
      phoneNumber: phoneNumber,
    };
  
    try {
      const response = await fetch(`${API_URL}/auth/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newUser),
      });
      if (response.status === 201) {
        showNotification('Registration successful! You can now log in.');
        window.location.href = 'login.html';
      } else {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Registration failed');
      }
    } catch (error) {
      console.error(error);
      showNotification('Failed to register: ' + error.message, 'error');
    }
  }
  