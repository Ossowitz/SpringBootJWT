package ru.iooko.springBootJwt.pojo;

import lombok.Data;
import ru.iooko.springBootJwt.model.Role;

import java.util.List;

@Data
public class JwtResponse {

    private Long id;

    private String username;

    private String email;

    private String token;

    private String type = "Bearer";

    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
