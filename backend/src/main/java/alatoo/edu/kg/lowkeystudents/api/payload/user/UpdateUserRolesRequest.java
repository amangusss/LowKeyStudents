package alatoo.edu.kg.lowkeystudents.api.payload.user;

import alatoo.edu.kg.lowkeystudents.api.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRolesRequest {
    private Set<Roles> roles;
}
