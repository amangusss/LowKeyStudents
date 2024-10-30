package alatoo.edu.kg.lowkeystudents_api.api.service;

import alatoo.edu.kg.lowkeystudents_api.api.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto updatePost(PostDto postDto);
    PostDto getPostById(String postId);
    List<PostDto> getAllPosts();
}
