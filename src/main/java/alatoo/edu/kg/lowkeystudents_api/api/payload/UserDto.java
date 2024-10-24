package alatoo.edu.kg.lowkeystudents_api.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record UserDto(
        @NotNull
        Long id,
        @NotNull
        String username,
        @NotNull
        String email,

        @NotNull
        String content,
        @NotNull
        String role,
        @NotNull
        @JsonProperty("created_at")
        Instant createdAt,
        @NotNull
        @JsonProperty("updated_at")
        Instant updatedAt
) {
}
