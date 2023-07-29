package ru.iooko.springBootJwt.pojo;

import lombok.Data;
import ru.iooko.springBootJwt.model.Role;

import java.util.Set;

@Data
public class JwtResponse {

    private Long id;

    private String username;

    private String email;

    private String token;

    private String type = "Bearer";

    private Set<Role> roles;

    public JwtResponse(Long id, String username, String email, String type, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.roles = roles;
    }
}
