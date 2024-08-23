package com.sparta.springsecondys.dto;

import com.sparta.springsecondys.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.userName = schedule.getUserName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
    }

}
