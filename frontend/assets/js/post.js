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
        showNotification('The post is not specified.', 'error');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/posts/${encodeURIComponent(postId)}`, {
            headers: {
                'Authorization': `Bearer ${authToken}`,
            },
        });
        if (!response.ok) throw new Error('An error occurred when receiving the post.');
        const post = await response.json();
        document.getElementById('post-title').textContent = post.title;
        document.getElementById('post-description').textContent = post.description;
        document.querySelector('.post-info strong').textContent = post.author.username;
        document.querySelector('.post-info em').textContent = new Date(post.createdAt).toLocaleString();
    } catch (error) {
        console.error(error);
        showNotification('The post could not be uploaded.', 'error');
    }
}
