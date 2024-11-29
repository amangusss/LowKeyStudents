document.addEventListener('DOMContentLoaded', () => {
  if (document.getElementById('user-list')) {
    displayUsers();
  }
});

async function displayUsers() {
  try {
    const response = await fetch(`${API_URL}/users`, {
      headers: {
        'Authorization': `Bearer ${authToken}`,
      },
    });
    if (!response.ok) throw new Error('Error fetching users');
    const users = await response.json();
    const userList = document.getElementById('user-list');
    userList.innerHTML = '';
    users.forEach(user => {
      const li = document.createElement('li');
      li.textContent = user.username;
      li.addEventListener('click', () => {
        // Используем userId вместо username
        window.location.href = `profile.html?userId=${encodeURIComponent(user.id)}`;
      });
      userList.appendChild(li);
    });
  } catch (error) {
    console.error(error);
    showNotification('Failed to load users.', 'error');
  }
}
