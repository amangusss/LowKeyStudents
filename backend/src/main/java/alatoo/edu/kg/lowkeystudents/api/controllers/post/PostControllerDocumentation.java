package alatoo.edu.kg.lowkeystudents.api.controllers.post;

import alatoo.edu.kg.lowkeystudents.api.payload.post.PostRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Endpoints for managing posts")
public sealed interface PostControllerDocumentation permits PostController {

    @Operation(summary = "Create a new post", description = "Creates a new post with title and description.")
    @PostMapping
    ResponseEntity<PostResponseDto> createPost(
            @Parameter(description = "Details of the new post", required = true)
            @Valid @RequestBody PostRequestDto dto);

    @Operation(summary = "Get all posts", description = "Fetches all available posts.")
    @GetMapping
    ResponseEntity<List<PostResponseDto>> getAllPosts();

    @Operation(summary = "Get post by ID", description = "Fetches a specific post by its ID.")
    @GetMapping("/{id}")
    ResponseEntity<PostResponseDto> getPostById(
            @Parameter(description = "ID of the post to be retrieved", required = true)
            @PathVariable Long id);

    @Operation(summary = "Update a post", description = "Updates a post by its ID.")
    @PutMapping("/{id}")
    ResponseEntity<PostResponseDto> updatePost(
            @Parameter(description = "ID of the post to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated details of the post", required = true) @Valid @RequestBody PostRequestDto dto);

    @Operation(summary = "Delete a post", description = "Deletes a post by its ID.")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePost(
            @Parameter(description = "ID of the post to be deleted", required = true)
            @PathVariable Long id);
}
