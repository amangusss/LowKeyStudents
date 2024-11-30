package alatoo.edu.kg.lowkeystudents.api.controllers.auth;

import alatoo.edu.kg.lowkeystudents.api.annotations.CurrentUser;
import alatoo.edu.kg.lowkeystudents.api.payload.token.TokenRefreshRequest;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;

import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public sealed interface AuthControllerDocumentation permits AuthController{

    @Operation(summary = "Register a new user", description = "Registers a new user with the given details.")
    @PostMapping("/register")
    ResponseEntity<UserDto> register(
            @Parameter(description = "User registration details", required = true)
            @RequestBody @Valid UserRegisterRequestDto dto);

    @Operation(summary = "Login a user", description = "Allows a registered user to login with username and password.")
    @PostMapping("/login")
    ResponseEntity<UserLoginResponseDto> login(
            @Parameter(description = "User login details", required = true)
            @RequestBody @Valid UserLoginRequestDto dto);

    @Operation(summary = "Refresh JWT Token", description = "Refreshes the JWT access token using a valid refresh token.")
    @PostMapping("/refresh-token")
    ResponseEntity<?> refreshToken(
            @Parameter(description = "Refresh token request", required = true)
            @RequestBody @Valid TokenRefreshRequest request);

    @Operation(summary = "Logout user", description = "Logs out the current user and invalidates the refresh token.")
    @PostMapping("/logout")
    ResponseEntity<?> logout();
}
