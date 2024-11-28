package alatoo.edu.kg.lowkeystudents.api.payload.user;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private String username;
    private Set<Roles> roles;
    private String accessToken;
}