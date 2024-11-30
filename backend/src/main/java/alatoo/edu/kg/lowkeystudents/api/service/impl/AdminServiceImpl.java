package alatoo.edu.kg.lowkeystudents.api.service.impl;

import alatoo.edu.kg.lowkeystudents.api.exceptions.NotFoundException;
import alatoo.edu.kg.lowkeystudents.api.mapper.UserMapper;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UpdateUserRolesRequest;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.service.AdminService;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import alatoo.edu.kg.lowkeystudents.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUserRoles(Long id, UpdateUserRolesRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setRoles(request.getRoles());
        UserEntity updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
