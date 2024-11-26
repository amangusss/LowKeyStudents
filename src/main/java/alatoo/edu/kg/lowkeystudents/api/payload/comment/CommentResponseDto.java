package alatoo.edu.kg.lowkeystudents.api.payload.comment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDto {
        Long id;
        String content;
        Instant createdAt;
        Instant updatedAt;
        String authorUsername;
        Long postId;
}
