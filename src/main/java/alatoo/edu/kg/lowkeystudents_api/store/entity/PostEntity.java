package alatoo.edu.kg.lowkeystudents_api.store.entity;

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

    String description;

    String author;

    Instant createdAt = Instant.now();

    Instant updatedAt = Instant.now();
}
