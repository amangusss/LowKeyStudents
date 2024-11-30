package alatoo.edu.kg.lowkeystudents.api.controllers.user;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserPublicDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for managing users")
public sealed interface UserControllerDocumentation permits UserController {

    @Operation(summary = "Get all users", description = "Fetches all users with public information.")
    @GetMapping
    ResponseEntity<List<UserPublicDto>> getAllUsers();

    @Operation(summary = "Get user by ID", description = "Fetches public information of a specific user by ID.")
    @GetMapping("/{id}")
    ResponseEntity<UserPublicDto> getUserById(
            @Parameter(description = "ID of the user to be retrieved", required = true)
            @PathVariable Long id);

    @Operation(summary = "Get user's posts", description = "Fetches all posts made by a specific user.")
    @GetMapping("/{id}/posts")
    ResponseEntity<List<PostResponseDto>> getUserPosts(
            @Parameter(description = "ID of the user whose posts are to be retrieved", required = true)
            @PathVariable Long id);

    @Operation(summary = "Get user's comments", description = "Fetches all comments made by a specific user.")
    @GetMapping("/{id}/comments")
    ResponseEntity<List<CommentResponseDto>> getUserComments(
            @Parameter(description = "ID of the user whose comments are to be retrieved", required = true)
            @PathVariable Long id);

    @Operation(summary = "Update user details", description = "Updates a specific user's details.")
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUser(
            @Parameter(description = "ID of the user to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated user details", required = true) @Valid @RequestBody UserDto dto);

    @Operation(summary = "Delete a user", description = "Deletes a specific user by ID.")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to be deleted", required = true)
            @PathVariable Long id);
}
