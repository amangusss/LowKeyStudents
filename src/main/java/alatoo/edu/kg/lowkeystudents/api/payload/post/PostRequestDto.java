package alatoo.edu.kg.lowkeystudents.api.payload.post;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDto {

    @NotBlank
    String title;

    @NotBlank
    String description;

    Long authorId;
}
