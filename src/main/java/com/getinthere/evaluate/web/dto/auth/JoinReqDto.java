package com.getinthere.evaluate.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.getinthere.evaluate.domain.user.Users;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class JoinReqDto {

    @Size(min = 2, max = 255)
    @NotBlank
    private String username;

    @Size(min = 4, max = 255)
    @NotBlank
    private String password;

    @Size(min = 4, max = 255)
    @NotBlank
    private String email;

    @Size(min = 2, max = 255)
    @NotBlank
    private String requestRole;

    public Users toEntity(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
        return Users.builder()
                .username(username)
                .password(password)
                .email(email)
                .requestRole(requestRole)
                .role("ROLE_GUEST")
                .build();
    }

}
