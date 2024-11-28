package alatoo.edu.kg.lowkeystudents.api.payload.post;

import alatoo.edu.kg.lowkeystudents.api.payload.comment.CommentResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserPublicDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponseDto {
    Long id;
    String title;
    String description;
    Instant createdAt;
    Instant updatedAt;
    UserPublicDto author;
    List<CommentResponseDto> comments;
}
