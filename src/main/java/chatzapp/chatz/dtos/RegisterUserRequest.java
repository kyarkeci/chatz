package chatzapp.chatz.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String userName;
    private String password;
    private String email;
}
