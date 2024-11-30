package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserPublicDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;

import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    UserDto toDTO(UserEntity user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserEntity toUserFromRegisterRequest(UserRegisterRequestDto dto);

    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    UserLoginResponseDto toLoginResponseDTO(UserEntity user);

    UserPublicDto toPublicDto(UserEntity user);

    default UserLoginResponseDto toLoginResponseDTOWithToken(UserEntity user, String accessToken) {
        UserLoginResponseDto dto = toLoginResponseDTO(user);
        dto.setAccessToken(accessToken);
        return dto;
    }
}