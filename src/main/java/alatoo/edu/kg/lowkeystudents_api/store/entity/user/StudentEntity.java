package alatoo.edu.kg.lowkeystudents_api.store.entity.user;

import alatoo.edu.kg.lowkeystudents_api.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "students")
public class StudentEntity extends BaseUserEntity{
    @Column(unique = true, nullable = false)
    String username;

    @OneToMany(mappedBy = "author" ,fetch = FetchType.LAZY)
    List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    List<CommentEntity> comments = new ArrayList<>();
}
