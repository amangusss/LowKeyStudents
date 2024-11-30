package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.user.*;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserPublicDto> getAllUsers();
    Optional<UserEntity> findByUsername(String username);
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
}