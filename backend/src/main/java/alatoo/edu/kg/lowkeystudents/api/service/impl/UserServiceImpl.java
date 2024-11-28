package alatoo.edu.kg.lowkeystudents.api.service.impl;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import alatoo.edu.kg.lowkeystudents.api.exceptions.*;
import alatoo.edu.kg.lowkeystudents.api.mapper.UserMapper;
import alatoo.edu.kg.lowkeystudents.api.payload.user.*;
import alatoo.edu.kg.lowkeystudents.api.service.JwtService;
import alatoo.edu.kg.lowkeystudents.api.service.UserService;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import alatoo.edu.kg.lowkeystudents.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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
                ));

        String jwtToken = jwtService.generateToken(user);

        return userMapper.toLoginResponseDTOWithToken(user, jwtToken);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserPublicDto> getAllUsers() {
        List<UserEntity> users = repository.findAll();
        return users.stream()
                .map(userMapper::toPublicDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (dto.getUsername() != null && !dto.getUsername().equals(user.getUsername())) {
            if (repository.existsByUsername(dto.getUsername())) {
                throw new UsernameTakenException();
            }
            user.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (repository.existsByEmail(dto.getEmail())) {
                throw new EmailTakenException();
            }
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().equals(user.getPhoneNumber())) {
            if (repository.existsByPhoneNumber(dto.getPhoneNumber())) {
                throw new PhoneTakenException();
            }
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        try {
            UserEntity updatedUser = repository.save(user);
            return userMapper.toDTO(updatedUser);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot update user: %s", e.getMessage()), e);
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        repository.deleteById(id);
    }
}