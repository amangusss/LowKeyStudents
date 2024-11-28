package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;

public interface UserService {

    UserDto register(UserRegisterRequestDto dto);
    UserLoginResponseDto login(UserLoginRequestDto dto);
    UserDto getUserById(Long id);
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
}