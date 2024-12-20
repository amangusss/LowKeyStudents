document.addEventListener('DOMContentLoaded', () => {
  if (document.getElementById('profile-username')) {
    loadProfile();
    loadUserPosts();
  }
});

function getQueryParam(param) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(param);
}

async function loadProfile() {
  const userId = getQueryParam('userId');
  if (!userId) {
    console.error('The userId is missing from the URL');
    showNotification('The user is not specified.', 'error');
    return;
  }

  try {
    const response = await fetch(`${API_URL}/users/${encodeURIComponent(userId)}`, {
      headers: {
        'Authorization': `Bearer ${authToken}`,
      },
    });

    if (!response.ok) throw new Error('Error receiving the profile');

    const user = await response.json();

    // Заполнение информации профиля
    document.getElementById('profile-username').textContent = user.username;
  } catch (error) {
    console.error(error);
    showNotification('The profile could not be uploaded.', 'error');
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

    if (!response.ok) throw new Error('Error when receiving user posts');

    const posts = await response.json();
    const userPosts = document.getElementById('user-posts');
    userPosts.innerHTML = '';

    if (posts.length === 0) {
      userPosts.innerHTML = '<p class="no-posts">There are no posts available.</p>';
      return;
    }

    posts.forEach(post => {
      const postDiv = document.createElement('div');
      postDiv.className = 'post';

      const title = document.createElement('h3');
      const link = document.createElement('a');
      link.href = `post.html?postId=${encodeURIComponent(post.id)}`;
      link.textContent = post.title;
      link.className = 'post-link';
      title.appendChild(link);

      const content = document.createElement('p');
      content.textContent = post.description;

      postDiv.appendChild(title);
      postDiv.appendChild(content);

      userPosts.appendChild(postDiv);
    });
  } catch (error) {
    console.error(error);
    showNotification('The user\'s posts could not be uploaded.', 'error');
  }
}

function showNotification(message, type) {
  const container = document.querySelector('.container');
  const notification = document.createElement('div');
  notification.className = `notification ${type}`;
  notification.textContent = message;
  container.prepend(notification);

  setTimeout(() => notification.remove(), 3000);
}
