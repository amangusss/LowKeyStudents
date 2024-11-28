// assets/js/profile.js

document.addEventListener('DOMContentLoaded', () => {
  if (document.getElementById('profile-username')) {
    loadProfile();
    loadUserPosts();
    // Удаляем загрузку комментариев
    // loadUserComments();
  }
});

function getQueryParam(param) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(param);
}

async function loadProfile() {
  const userId = getQueryParam('userId');
  if (!userId) {
    showNotification('Пользователь не указан.', 'error');
    return;
  }

  try {
    const response = await fetch(`${API_URL}/users/${encodeURIComponent(userId)}`, {
      headers: {
        'Authorization': `Bearer ${authToken}`,
      },
    });
    if (!response.ok) throw new Error('Ошибка при получении профиля');
    const user = await response.json();
    document.getElementById('profile-username').textContent = user.username;
    // Не отображаем email и phoneNumber
  } catch (error) {
    console.error(error);
    showNotification('Не удалось загрузить профиль.', 'error');
  }
}

async function loadUserPosts() {
  const userId = getQueryParam('userId');
  if (!userId) return;

  try {
    const response = await fetch(`${API_URL}/users/${encodeURIComponent(userId)}/posts`, {
      headers: {
        'Authorization': `Bearer ${authToken}`,
      },
    });
    if (!response.ok) throw new Error('Ошибка при получении постов пользователя');
    const posts = await response.json();
    const userPosts = document.getElementById('user-posts');
    userPosts.innerHTML = '';
    if (posts.length === 0) {
      userPosts.innerHTML = '<p>Нет доступных постов.</p>';
      return;
    }
    posts.forEach(post => {
      const postDiv = document.createElement('div');
      postDiv.className = 'post';

      const title = document.createElement('h3');
      // Делаем заголовок кликабельным
      const link = document.createElement('a');
      link.href = `post.html?postId=${encodeURIComponent(post.id)}`;
      link.textContent = post.title;
      link.style.textDecoration = 'none';
      link.style.color = '#007BFF'; // Цвет ссылки
      title.appendChild(link);

      const content = document.createElement('p');
      content.textContent = post.description;

      postDiv.appendChild(title);
      postDiv.appendChild(content);

      userPosts.appendChild(postDiv);
    });
  } catch (error) {
    console.error(error);
    showNotification('Не удалось загрузить посты пользователя.', 'error');
  }
}
