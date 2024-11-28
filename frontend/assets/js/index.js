// index.js

document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('post-list')) {
      displayPosts();
  
      const addPostButton = document.getElementById('add-post-button');
      addPostButton.addEventListener('click', addNewPost);
  
      // Add Enter key functionality for adding posts
      const postTitleInput = document.getElementById('post-title');
      const postContentInput = document.getElementById('post-content');
  
      postTitleInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
          e.preventDefault();
          addNewPost();
        }
      });
  
      postContentInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter' && !e.shiftKey) { // Allow Shift+Enter for new lines
          e.preventDefault();
          addNewPost();
        }
      });
    }
  });
  
  async function displayPosts() {
    try {
      const response = await fetch(`${API_URL}/posts`, {
        headers: {
          'Authorization': `Bearer ${authToken}`,
        },
      });
      if (!response.ok) throw new Error('Error fetching posts');
      const posts = await response.json();
      const postList = document.getElementById('post-list');
      postList.innerHTML = '';
      posts.forEach(post => {
        const postDiv = document.createElement('div');
        postDiv.className = 'post';
  
        const title = document.createElement('h3');
        title.textContent = post.title;
  
        const author = document.createElement('span');
        author.className = 'author';
        author.textContent = `by ${post.authorUsername}`;
  
        title.appendChild(author);
  
        const content = document.createElement('p');
        content.textContent = post.description;
  
        const commentListDiv = document.createElement('div');
        commentListDiv.className = 'comment-list';
  
        const commentTitle = document.createElement('h4');
        commentTitle.textContent = 'Comments:';
  
        const commentUl = document.createElement('ul');
        post.comments.forEach(comment => {
          const commentLi = document.createElement('li');
          commentLi.innerHTML = `<strong>${comment.authorUsername}:</strong> ${comment.content}`;
          commentUl.appendChild(commentLi);
        });
  
        commentListDiv.appendChild(commentTitle);
        commentListDiv.appendChild(commentUl);
  
        const addCommentDiv = document.createElement('div');
        addCommentDiv.className = 'add-comment';
  
        const commentInput = document.createElement('input');
        commentInput.type = 'text';
        commentInput.placeholder = 'Add a comment';
  
        const commentButton = document.createElement('button');
        commentButton.textContent = 'Submit';
  
        // Enable submitting comments with Enter key
        commentInput.addEventListener('keypress', (e) => {
          if (e.key === 'Enter') {
            e.preventDefault();
            commentButton.click();
          }
        });
  
        commentButton.addEventListener('click', () => {
          const text = commentInput.value.trim();
          if (text === '') {
            showNotification('Please enter a comment.', 'error');
            return;
          }
          addComment(post.id, text);
          commentInput.value = '';
        });
  
        addCommentDiv.appendChild(commentInput);
        addCommentDiv.appendChild(commentButton);
  
        commentListDiv.appendChild(addCommentDiv);
  
        postDiv.appendChild(title);
        postDiv.appendChild(content);
        postDiv.appendChild(commentListDiv);
  
        postList.appendChild(postDiv);
      });
    } catch (error) {
      console.error(error);
      showNotification('Failed to load posts.', 'error');
    }
  }
  
  async function addNewPost() {
    const titleInput = document.getElementById('post-title');
    const contentInput = document.getElementById('post-content');
  
    const title = titleInput.value.trim();
    const content = contentInput.value.trim();
  
    if (title === '' || content === '') {
      showNotification('Please fill in all fields.', 'error');
      return;
    }
  
    const newPost = {
      title: title,
      description: content,
    };
  
    try {
      const response = await fetch(`${API_URL}/posts`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${authToken}`,
        },
        body: JSON.stringify(newPost),
      });
      if (!response.ok) throw new Error('Error adding post');
      const createdPost = await response.json();
      displayPosts();
      titleInput.value = '';
      contentInput.value = '';
      showNotification('Post added successfully!');
    } catch (error) {
      console.error(error);
      showNotification('Failed to add post.', 'error');
    }
  }
  
  async function addComment(postId, commentText) {
    const newComment = {
      content: commentText,
    };
  
    try {
      const response = await fetch(`${API_URL}/posts/${postId}/comments`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${authToken}`,
        },
        body: JSON.stringify(newComment),
      });
      if (!response.ok) throw new Error('Error adding comment');
      const createdComment = await response.json();
      displayPosts();
      showNotification('Comment added successfully!');
    } catch (error) {
      console.error(error);
      showNotification('Failed to add comment.', 'error');
    }
  }
  