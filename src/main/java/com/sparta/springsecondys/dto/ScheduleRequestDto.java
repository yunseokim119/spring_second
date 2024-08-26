package com.sparta.springsecondys.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long userId;
    private String title;
    private String content;

    public ScheduleRequestDto() {
    }

    public ScheduleRequestDto(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
