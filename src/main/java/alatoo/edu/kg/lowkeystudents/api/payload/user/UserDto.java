package alatoo.edu.kg.lowkeystudents.api.payload.user;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Set<Roles> roles;
}
