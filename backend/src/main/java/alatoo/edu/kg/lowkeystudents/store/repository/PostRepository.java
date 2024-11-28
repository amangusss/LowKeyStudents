package alatoo.edu.kg.lowkeystudents.store.repository;

import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByAuthorId(Long userId);
}
