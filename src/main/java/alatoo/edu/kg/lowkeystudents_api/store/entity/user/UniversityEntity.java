package alatoo.edu.kg.lowkeystudents_api.store.entity.user;

import alatoo.edu.kg.lowkeystudents_api.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "universities")
public class UniversityEntity extends BaseUserEntity{
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "address", nullable = false, unique = true)
    String address;

    @Column(name = "description", nullable = false, unique = true)
    String description;

    @OneToMany(mappedBy = "author")
    List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    List<CommentEntity> comments = new ArrayList<>();
}
