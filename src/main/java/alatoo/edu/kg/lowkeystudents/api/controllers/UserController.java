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
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterRequestDto dto) {
        UserDto registeredUser = userService.register(dto);
        return ResponseEntity.status(201).body(registeredUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto dto) {
        UserLoginResponseDto loginResponse = userService.login(dto);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        UserDto updatedUser = userService.update(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}