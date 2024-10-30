package alatoo.edu.kg.lowkeystudents_api.store.repository;

import alatoo.edu.kg.lowkeystudents_api.store.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
