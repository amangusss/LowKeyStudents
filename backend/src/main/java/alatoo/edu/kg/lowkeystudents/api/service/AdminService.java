package alatoo.edu.kg.lowkeystudents.api.service;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UpdateUserRolesRequest;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
    UserDto updateUserRoles(Long id, UpdateUserRolesRequest request);
    void deleteUser(Long id);
}
