package alatoo.edu.kg.lowkeystudents_api.api.service.impl;

import alatoo.edu.kg.lowkeystudents_api.api.mapper.PostMapper;
import alatoo.edu.kg.lowkeystudents_api.api.payload.PostDto;
import alatoo.edu.kg.lowkeystudents_api.api.service.PostService;
import alatoo.edu.kg.lowkeystudents_api.store.repository.PostRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    @Override
    @Transactional
    public PostDto createPost(PostDto postDto) {
        return null;
    }

    @Override
    @Transactional
    public PostDto updatePost(PostDto postDto) {
        return null;
    }

    @Override
    @Transactional
    public PostDto getPostById(String postId) {
        return null;
    }

    @Override
    @Transactional
    public List<PostDto> getAllPosts() {
        return List.of();
    }
}
