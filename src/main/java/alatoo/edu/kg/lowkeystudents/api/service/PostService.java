package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.post.PostRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto createPost(PostRequestDto dto);
    PostResponseDto getPostById(Long id);
    PostResponseDto updatePost(Long id, PostRequestDto dto);
    void deletePost(Long id);
    List<PostResponseDto> getAllPosts();
}
