package com.sparta.springsecondys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignedUserDto {

    private Long id;  // 유저 ID
    private String username;  // 유저명
    private String email;  // 이메일

    public AssignedUserDto() {
    }

    public AssignedUserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
