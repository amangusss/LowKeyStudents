package alatoo.edu.kg.lowkeystudents.api.service.impl;

import alatoo.edu.kg.lowkeystudents.api.exceptions.NotFoundException;
import alatoo.edu.kg.lowkeystudents.api.mapper.CommentMapper;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.api.service.CommentService;
import alatoo.edu.kg.lowkeystudents.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import alatoo.edu.kg.lowkeystudents.store.repository.CommentRepository;
import alatoo.edu.kg.lowkeystudents.store.repository.PostRepository;
import alatoo.edu.kg.lowkeystudents.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto createComment(CommentRequestDto dto) {
        CommentEntity comment = commentMapper.toEntity(dto);

        UserEntity currentUser = getCurrentUser();
        comment.setAuthor(currentUser);

        PostEntity post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + dto.getPostId()));
        comment.setPost(post);

        CommentEntity savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public CommentResponseDto getCommentById(Long id) {
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        CommentEntity existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existingComment.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to update this comment");
        }

        existingComment.setContent(dto.getContent());

        CommentEntity updatedComment = commentRepository.save(existingComment);
        return commentMapper.toDto(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        CommentEntity existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!existingComment.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this comment");
        }

        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepository.findByPost(post).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }
}
