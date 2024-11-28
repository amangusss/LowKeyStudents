// profile.js

document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('profile-username')) {
      loadProfile();
      loadUserPosts();
      loadUserComments();
    }
  });
  
  async function loadProfile() {
    try {
      const response = await fetch(`${API_URL}/users/me`, {
        headers: {
          'Authorization': `Bearer ${authToken}`,
        },
      });
      if (!response.ok) throw new Error('Error fetching profile');
      const user = await response.json();
      document.getElementById('profile-username').textContent = user.username;
      document.getElementById('profile-email').textContent = user.email;
      document.getElementById('profile-phone').textContent = user.phoneNumber;
    } catch (error) {
      console.error(error);
      showNotification('Failed to load profile.', 'error');
    }
  }
  
  async function loadUserPosts() {
    try {
      const response = await fetch(`${API_URL}/users/me/posts`, {
        headers: {
          'Authorization': `Bearer ${authToken}`,
        },
      });
      if (!response.ok) throw new Error('Error fetching user posts');
      const posts = await response.json();
      const userPosts = document.getElementById('user-posts');
      userPosts.innerHTML = '';
      posts.forEach(post => {
        const postDiv = document.createElement('div');
        postDiv.className = 'post';
  
        const title = document.createElement('h3');
        title.textContent = post.title;
  
        const content = document.createElement('p');
        content.textContent = post.description;
  
        postDiv.appendChild(title);
        postDiv.appendChild(content);
  
        userPosts.appendChild(postDiv);
      });
    } catch (error) {
      console.error(error);
      showNotification('Failed to load user posts.', 'error');
    }
  }
  
  async function loadUserComments() {
    try {
      const response = await fetch(`${API_URL}/users/me/comments`, {
        headers: {
          'Authorization': `Bearer ${authToken}`,
        },
      });
      if (!response.ok) throw new Error('Error fetching user comments');
      const comments = await response.json();
      const userComments = document.getElementById('user-comments');
      userComments.innerHTML = '';
      comments.forEach(comment => {
        const commentDiv = document.createElement('div');
        commentDiv.className = 'comment';
  
        const content = document.createElement('p');
        content.innerHTML = `<strong>On Post "${comment.postTitle}":</strong> ${comment.content}`;
  
        commentDiv.appendChild(content);
        userComments.appendChild(commentDiv);
      });
    } catch (error) {
      console.error(error);
      showNotification('Failed to load user comments.', 'error');
    }
  }
  