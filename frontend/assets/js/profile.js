document.addEventListener('DOMContentLoaded', () => {
  if (document.getElementById('profile-username')) {
    loadProfile();
    loadUserPosts();
  }
});

// Получение параметра userId из URL
function getQueryParam(param) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(param);
}

// Загрузка профиля пользователя
async function loadProfile() {
  const userId = getQueryParam('userId');
  if (!userId) {
    console.error('userId отсутствует в URL');
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

    // Заполнение информации профиля
    document.getElementById('profile-username').textContent = user.username;
  } catch (error) {
    console.error(error);
    showNotification('Не удалось загрузить профиль.', 'error');
  }
}

// Загрузка постов пользователя
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
    userPosts.innerHTML = ''; // Очистка перед добавлением постов

    if (posts.length === 0) {
      userPosts.innerHTML = '<p class="no-posts">Нет доступных постов.</p>';
      return;
    }

    posts.forEach(post => {
      const postDiv = document.createElement('div');
      postDiv.className = 'post';

      // Создание заголовка с ссылкой
      const title = document.createElement('h3');
      const link = document.createElement('a');
      link.href = `post.html?postId=${encodeURIComponent(post.id)}`;
      link.textContent = post.title;
      link.className = 'post-link';
      title.appendChild(link);

      // Добавление описания поста
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

// Отображение уведомлений
function showNotification(message, type) {
  const container = document.querySelector('.container');
  const notification = document.createElement('div');
  notification.className = `notification ${type}`;
  notification.textContent = message;
  container.prepend(notification);

  // Удаление уведомления через 3 секунды
  setTimeout(() => notification.remove(), 3000);
}
