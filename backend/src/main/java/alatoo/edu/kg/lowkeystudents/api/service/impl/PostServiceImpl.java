package alatoo.edu.kg.lowkeystudents.api.service.impl;

import alatoo.edu.kg.lowkeystudents.api.exceptions.NotFoundException;
import alatoo.edu.kg.lowkeystudents.api.mapper.PostMapper;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import alatoo.edu.kg.lowkeystudents.api.service.PostService;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import alatoo.edu.kg.lowkeystudents.store.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostResponseDto createPost(PostRequestDto dto) {
        PostEntity post = postMapper.toEntity(dto);

        UserEntity currentUser = getCurrentUser();
        post.setAuthor(currentUser);

        PostEntity savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    @Override
    public PostResponseDto getPostById(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toDto(post);
    }

    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto dto) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));

        UserEntity currentUser = getCurrentUser();
        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not the author of this post");
        }

        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setUpdatedAt(Instant.now());

        PostEntity updatedPost = postRepository.save(post);
        return postMapper.toDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        PostEntity existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existingPost.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this post");
        }

        postRepository.deleteById(id);
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getPostsByUserId(Long userId) {
        List<PostEntity> posts = postRepository.findByAuthorId(userId);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }
}