package alatoo.edu.kg.lowkeystudents.api.payload.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDto {

        @NotBlank
        String content;

        Long authorId;

        Long postId;
}
