package alatoo.edu.kg.lowkeystudents_api.api.payload;


import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public record UserDto(
        Long id,
        String username,
        String email,
        String typeOfUser,
        List<PostEntity> posts
) {
}
