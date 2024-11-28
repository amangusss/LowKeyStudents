// src/main/resources/static/js/post.js

document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('post-title')) {
        loadPost();
    }
});

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

async function loadPost() {
    const postId = getQueryParam('postId');
    if (!postId) {
        showNotification('Пост не указан.', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/posts/${encodeURIComponent(postId)}`, {
            headers: {
                'Authorization': `Bearer ${authToken}`,
            },
        });
        if (!response.ok) throw new Error('Ошибка при получении поста');
        const post = await response.json();
        document.getElementById('post-title').textContent = post.title;
        document.getElementById('post-description').textContent = post.description;
        document.querySelector('.post-info strong').textContent = post.author.username;
        document.querySelector('.post-info em').textContent = new Date(post.createdAt).toLocaleString();
        // Добавьте другие поля, если необходимо
    } catch (error) {
        console.error(error);
        showNotification('Не удалось загрузить пост.', 'error');
    }
}
