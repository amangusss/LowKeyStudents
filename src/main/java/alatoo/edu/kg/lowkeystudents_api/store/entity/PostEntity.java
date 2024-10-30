package alatoo.edu.kg.lowkeystudents_api.store.entity;

import alatoo.edu.kg.lowkeystudents_api.store.entity.user.BaseUserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @JsonProperty("created_at")
    @Column(name = "created_at", updatable = false)
    Instant createdAt = Instant.now();

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<CommentEntity> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    BaseUserEntity author;
}
