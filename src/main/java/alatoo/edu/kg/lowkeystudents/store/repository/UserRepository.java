package alatoo.edu.kg.lowkeystudents.store.repository;

import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);

    Optional<UserEntity> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}