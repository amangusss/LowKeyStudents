package alatoo.edu.kg.lowkeystudents.api.service.impl;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import alatoo.edu.kg.lowkeystudents.api.exceptions.*;
import alatoo.edu.kg.lowkeystudents.api.mapper.UserMapper;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.api.service.AuthService;
import alatoo.edu.kg.lowkeystudents.api.service.RefreshTokenService;
import alatoo.edu.kg.lowkeystudents.api.utils.JwtUtils;
import alatoo.edu.kg.lowkeystudents.store.entity.RefreshTokenEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import alatoo.edu.kg.lowkeystudents.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public UserDto register(UserRegisterRequestDto dto) {

        if (repository.existsByUsername(dto.username())) {
            throw new UsernameTakenException();
        }

        if (repository.existsByEmail(dto.email())) {
            throw new EmailTakenException();
        }

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new PhoneTakenException();
        }

        UserEntity user = userMapper.toUserFromRegisterRequest(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(Roles.USER));

        try {
            UserEntity savedUser = repository.save(user);
            return userMapper.toDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot register user: %s", e.getMessage()), e);
        }
    }

    @Override
    public UserDto registerAdmin(UserRegisterRequestDto dto) {
        if (repository.existsByUsername(dto.username())) {
            throw new UsernameTakenException();
        }
        if (repository.existsByEmail(dto.email())) {
            throw new EmailTakenException();
        }
        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new PhoneTakenException();
        }

        UserEntity user = userMapper.toUserFromRegisterRequest(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(Roles.ADMIN));

        try {
            UserEntity savedUser = repository.save(user);
            return userMapper.toDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot register admin: %s", e.getMessage()), e);
        }
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        UserEntity user = repository.findByUsernameOrEmailOrPhoneNumber(dto.login(), dto.login(), dto.login())
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new AuthException("Incorrect password!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        dto.password()
                )
        );

        String jwtToken = jwtUtils.generateToken(user);
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user);

        return new UserLoginResponseDto(
                user.getUsername(),
                user.getRoles(),
                jwtToken,
                refreshToken.getToken()
        );
    }
}
