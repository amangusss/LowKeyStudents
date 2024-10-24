package alatoo.edu.kg.lowkeystudents_api.store.repository;

import alatoo.edu.kg.lowkeystudents_api.store.entity.user.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {
}
