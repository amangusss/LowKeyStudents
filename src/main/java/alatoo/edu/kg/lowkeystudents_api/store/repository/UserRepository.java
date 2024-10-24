package alatoo.edu.kg.lowkeystudents_api.store.repository;

import alatoo.edu.kg.lowkeystudents_api.store.entity.user.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<StudentEntity, Long> {

}
