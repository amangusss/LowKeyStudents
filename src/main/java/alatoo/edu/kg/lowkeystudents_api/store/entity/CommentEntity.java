package alatoo.edu.kg.lowkeystudents_api.store.entity;

import alatoo.edu.kg.lowkeystudents_api.store.entity.user.BaseUserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "COMMENTS")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "CONTENT", nullable = false)
    String content;

    @JsonProperty("created_at")
    @Column(name = "CREATED_AT", updatable = false)
    Instant createdAt = Instant.now();

    @JsonProperty("updated_at")
    @Column(name = "UPDATED_AT")
    Instant updatedAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    BaseUserEntity author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    PostEntity post;
}

