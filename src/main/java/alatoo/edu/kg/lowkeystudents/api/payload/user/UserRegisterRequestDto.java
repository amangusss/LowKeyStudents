package alatoo.edu.kg.lowkeystudents.api.payload.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDto(
        @NotBlank(message = "Username is empty")
        String username,

        @NotBlank(message = "Password is empty")
        String password,

        @NotBlank(message = "Email is empty")
        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Phone number is empty")
        String phoneNumber
) {}
