const API_URL = 'http://localhost:8081/api';
let authToken = localStorage.getItem('authToken') || '';

function showNotification(message, type = 'success') {
  const container = document.getElementById('notification-container');
  if (!container) return;
  
  const notification = document.createElement('div');
  notification.className = `notification ${type}`;
  notification.textContent = message;
  container.appendChild(notification);

  notification.addEventListener('animationend', () => {
    notification.remove();
  });
}

function logout() {
  authToken = '';
  localStorage.removeItem('authToken');
  window.location.href = 'login.html';
}
