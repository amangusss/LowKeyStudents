package alatoo.edu.kg.lowkeystudents.api.controllers;

import alatoo.edu.kg.lowkeystudents.api.payload.user.UserDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginRequestDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserLoginResponseDto;
import alatoo.edu.kg.lowkeystudents.api.payload.user.UserRegisterRequestDto;
import alatoo.edu.kg.lowkeystudents.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterRequestDto dto) {
        UserDto registeredUser = userService.register(dto);
        return ResponseEntity.status(201).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto dto) {
        UserLoginResponseDto loginResponse = userService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }
}
