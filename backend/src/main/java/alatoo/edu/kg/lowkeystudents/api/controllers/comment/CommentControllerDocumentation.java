package alatoo.edu.kg.lowkeystudents.api.controllers.comment;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Endpoints for managing comments")
public sealed interface CommentControllerDocumentation permits CommentController {

    @Operation(summary = "Create a comment", description = "Creates a comment under a specified post.")
    @PostMapping("/posts/{postId}")
    ResponseEntity<CommentResponseDto> createComment(
            @Parameter(description = "ID of the post to add comment to", required = true) @PathVariable Long postId,
            @Parameter(description = "Details of the comment to be created", required = true) @Valid @RequestBody CommentRequestDto dto);

    @Operation(summary = "Get comments by post ID", description = "Fetches all comments related to a specific post.")
    @GetMapping("/posts/{postId}")
    ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(
            @Parameter(description = "ID of the post whose comments are to be retrieved", required = true) @PathVariable Long postId);

    @Operation(summary = "Get comment by ID", description = "Fetches a specific comment by ID.")
    @GetMapping("/{id}")
    ResponseEntity<CommentResponseDto> getCommentById(
            @Parameter(description = "ID of the comment to be retrieved", required = true) @PathVariable Long id);

    @Operation(summary = "Update a comment", description = "Updates a specific comment by ID.")
    @PutMapping("/{id}")
    ResponseEntity<CommentResponseDto> updateComment(
            @Parameter(description = "ID of the comment to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated comment details", required = true) @Valid @RequestBody CommentRequestDto dto);

    @Operation(summary = "Delete a comment", description = "Deletes a specific comment by ID.")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteComment(
            @Parameter(description = "ID of the comment to be deleted", required = true) @PathVariable Long id);
}
