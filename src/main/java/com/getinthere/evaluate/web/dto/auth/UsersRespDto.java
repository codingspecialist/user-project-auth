package com.getinthere.evaluate.web.dto.auth;

import java.time.LocalDateTime;

import com.getinthere.evaluate.domain.user.Users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UsersRespDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String requestRole;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public UsersRespDto(Long id, String username, String email, String role, String requestRole,
            LocalDateTime createDate,
            LocalDateTime updateDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.requestRole = requestRole;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static UsersRespDto toDto(Users user) {
        return UsersRespDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .requestRole(user.getRequestRole())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .build();
    }
}
