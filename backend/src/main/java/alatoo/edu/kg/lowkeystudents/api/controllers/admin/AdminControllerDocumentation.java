package alatoo.edu.kg.lowkeystudents.api.controllers.admin;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UpdateUserRolesRequest;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin", description = "Administrative endpoints")
@RequestMapping("api/admin")
public sealed interface AdminControllerDocumentation permits AdminController {

    @Operation(summary = "Get all users", description = "Retrieve all users")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/users")
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(summary = "Update user roles", description = "Update roles of a user")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/admin/users/{id}/roles")
    ResponseEntity<UserDto> updateUserRoles(
            @Parameter(description = "ID of the user to update roles", required = true)
            @PathVariable Long id,
            @Parameter(description = "New roles for the user", required = true)
            @Valid @RequestBody UpdateUserRolesRequest request);

    @Operation(summary = "Delete a user", description = "Delete a user by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/admin/users/{id}")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete", required = true)
            @PathVariable Long id);
}
