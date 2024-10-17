package alatoo.edu.kg.lowkeystudents_api.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true, nullable = false)
    String title;

    @Column(unique = true, nullable = false)
    String description;

    String author;

    @JsonProperty("created_at")
    Instant createdAt = Instant.now();

    @JsonProperty("updated_at")
    Instant updatedAt = Instant.now();
}
