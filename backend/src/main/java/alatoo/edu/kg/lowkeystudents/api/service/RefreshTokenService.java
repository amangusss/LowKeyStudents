package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.store.entity.RefreshTokenEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshTokenEntity> findByToken(String token);
    RefreshTokenEntity createRefreshToken(UserEntity user);
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);
    void deleteByUserId(Long userId);
}
