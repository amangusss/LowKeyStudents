package alatoo.edu.kg.lowkeystudents_api.api.payload.user;


import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDto(
        @NotNull
        Long id,
        @NotNull
        String username,
        @NotNull
        String email,
        @NotNull
        boolean isStudent,
        @NotNull
        List<PostEntity> posts
) {
}
