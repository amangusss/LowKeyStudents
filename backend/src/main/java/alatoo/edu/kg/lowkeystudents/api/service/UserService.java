package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.user.*;

import java.util.List;

public interface UserService {

    UserDto register(UserRegisterRequestDto dto);
    UserLoginResponseDto login(UserLoginRequestDto dto);
    UserDto getUserById(Long id);
    List<UserPublicDto> getAllUsers();
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
}