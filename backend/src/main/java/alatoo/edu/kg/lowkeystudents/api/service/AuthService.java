package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;

public interface AuthService {
    UserDto register(UserRegisterRequestDto dto);
    UserDto registerAdmin(UserRegisterRequestDto dto);
    UserLoginResponseDto login(UserLoginRequestDto dto);
}
