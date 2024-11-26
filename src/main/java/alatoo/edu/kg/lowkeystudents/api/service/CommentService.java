package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(CommentRequestDto dto);
    CommentResponseDto getCommentById(Long id);
    CommentResponseDto updateComment(Long id, CommentRequestDto dto);
    void deleteComment(Long id);
    List<CommentResponseDto> getCommentsByPostId(Long postId);
}