package alatoo.edu.kg.lowkeystudents_api.api.payload;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UniversityDto(
        @NotNull
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String address,
        @NotNull
        String email,
        @NotNull
        String phoneNumber,

        @NotNull
        List<CommentDto> comments,
        List<PostDto> posts
) {
}
