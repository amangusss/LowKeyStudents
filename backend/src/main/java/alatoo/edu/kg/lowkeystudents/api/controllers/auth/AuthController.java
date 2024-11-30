package alatoo.edu.kg.lowkeystudents.api.controllers.auth;

import alatoo.edu.kg.lowkeystudents.api.annotations.CurrentUser;
import alatoo.edu.kg.lowkeystudents.api.exceptions.TokenRefreshException;
import alatoo.edu.kg.lowkeystudents.api.payload.token.TokenRefreshRequest;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.api.service.AuthService;
import alatoo.edu.kg.lowkeystudents.api.service.RefreshTokenService;
import alatoo.edu.kg.lowkeystudents.api.service.UserService;

import alatoo.edu.kg.lowkeystudents.api.utils.JwtUtils;
import alatoo.edu.kg.lowkeystudents.store.entity.RefreshTokenEntity;
import alatoo.edu.kg.lowkeystudents.store.entity.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public final class AuthController implements AuthControllerDocumentation {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterRequestDto dto) {
        UserDto registeredUser = authService.register(dto);
        return ResponseEntity.status(201).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto dto) {
        UserLoginResponseDto loginResponse = authService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        UserEntity user = (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            refreshTokenService.deleteByUserId(user.getId());
            return ResponseEntity.ok("You have successfully logged out.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The user is not logged in.");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEntity::getUser)
                .map(user -> {
                    String newAccessToken = jwtUtils.generateToken(user);
                    String newRefreshToken = refreshTokenService.createRefreshToken(user).getToken();

                    UserLoginResponseDto response = new UserLoginResponseDto(
                            user.getUsername(),
                            user.getRoles(),
                            newAccessToken,
                            newRefreshToken
                    );

                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token was not found."));
    }
}
