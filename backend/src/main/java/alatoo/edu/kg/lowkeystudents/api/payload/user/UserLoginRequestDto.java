package alatoo.edu.kg.lowkeystudents.api.payload.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank(message = "Login is empty")
        String login,

        @NotBlank(message = "Password is empty")
        String password
) {}
