package alatoo.edu.kg.lowkeystudents_api.store.repository;

import alatoo.edu.kg.lowkeystudents_api.store.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
