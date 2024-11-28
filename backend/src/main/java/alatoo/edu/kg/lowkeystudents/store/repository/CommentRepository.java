package alatoo.edu.kg.lowkeystudents.store.repository;

import alatoo.edu.kg.lowkeystudents.store.entity.CommentEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByAuthorId(Long userId);
    List<CommentEntity> findByPost(PostEntity post);
}
