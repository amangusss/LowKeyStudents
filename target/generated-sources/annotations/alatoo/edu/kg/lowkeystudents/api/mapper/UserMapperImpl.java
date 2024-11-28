package alatoo.edu.kg.lowkeystudents.api.mapper;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T15:07:07+0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDTO(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.username( user.getUsername() );
        userDto.email( user.getEmail() );
        userDto.phoneNumber( user.getPhoneNumber() );
        Set<Roles> set = user.getRoles();
        if ( set != null ) {
            userDto.roles( new LinkedHashSet<Roles>( set ) );
        }

        return userDto.build();
    }

    @Override
    public UserEntity toUserFromRegisterRequest(UserRegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( dto.username() );
        userEntity.setPassword( dto.password() );
        userEntity.setEmail( dto.email() );
        userEntity.setPhoneNumber( dto.phoneNumber() );

        return userEntity;
    }

    @Override
    public UserLoginResponseDto toLoginResponseDTO(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserLoginResponseDto.UserLoginResponseDtoBuilder userLoginResponseDto = UserLoginResponseDto.builder();

        userLoginResponseDto.username( user.getUsername() );
        Set<Roles> set = user.getRoles();
        if ( set != null ) {
            userLoginResponseDto.roles( new LinkedHashSet<Roles>( set ) );
        }

        return userLoginResponseDto.build();
    }
}
