package alatoo.edu.kg.lowkeystudents.api.controllers.user;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.post.PostResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserPublicDto;
import alatoo.edu.kg.lowkeystudents.api.service.CommentService;
import alatoo.edu.kg.lowkeystudents.api.service.PostService;
import alatoo.edu.kg.lowkeystudents.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public final class UserController implements UserControllerDocumentation {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<UserPublicDto>> getAllUsers() {
        List<UserPublicDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        UserPublicDto publicUser = new UserPublicDto(user.getId(), user.getUsername());
        return ResponseEntity.ok(publicUser);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponseDto>> getUserPosts(@PathVariable Long id) {
        List<PostResponseDto> posts = postService.getPostsByUserId(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getUserComments(@PathVariable Long id) {
        List<CommentResponseDto> comments = commentService.getCommentsByUserId(id);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        UserDto updatedUser = userService.update(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
