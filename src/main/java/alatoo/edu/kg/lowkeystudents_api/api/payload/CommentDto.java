package alatoo.edu.kg.lowkeystudents_api.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record CommentDto(
        @NotNull
        Long id,
        @NotNull
        String content,
        @NotNull
        String author,
        @NotNull
        @JsonProperty("created_at")
        Instant createdAt,
        @NotNull
        @JsonProperty("updated_at")
        Instant updatedAt
) {
}
