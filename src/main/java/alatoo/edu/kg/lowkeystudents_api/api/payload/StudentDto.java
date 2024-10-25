package alatoo.edu.kg.lowkeystudents_api.api.payload;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.time.Instant;

public record StudentDto(
        @NotNull
        Long id,
        @NotNull
        String username,
        @NotNull
        String email,
        @NotNull
        String phoneNumber,

        @NotNull
        List<PostDto> posts,
        List<CommentDto> comments


) {
}
