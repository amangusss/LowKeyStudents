document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
      loginForm.addEventListener('submit', loginUser);
    }

    const loginUsername = document.getElementById('login-username');
    const loginPassword = document.getElementById('login-password');
  
    loginUsername.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        loginUser(e);
      }
    });
  
    loginPassword.addEventListener('keypress', (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        loginUser(e);
      }
    });
  });
  
  async function loginUser(event) {
    event.preventDefault();
    const username = document.getElementById('login-username').value.trim();
    const password = document.getElementById('login-password').value.trim();
  
    if (username === '' || password === '') {
      showNotification('Please fill in all fields.', 'error');
      return;
    }
  
    const credentials = {
      login: username,
      password: password,
    };
  
    try {
      const response = await fetch(`${API_URL}/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      });
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Login failed');
      }
      const data = await response.json();
      data.accessToken = undefined;
      authToken = data.accessToken;
      localStorage.setItem('authToken', authToken);
      showNotification('Login successful!');
      window.location.href = 'index.html';
    } catch (error) {
      console.error(error);
      showNotification('Failed to login: ' + error.message, 'error');
    }
  }
  