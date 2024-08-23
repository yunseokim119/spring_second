package com.sparta.springsecondys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String comment;
    private String username;
    private Long scheduleId;

    public String getContent() {
        return comment;
    }

    public String getUserName() {
        return username;
    }
}
