package com.sparta.springsecondys.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ScheduleRequestDto {
    private Long ownerId;
    private String title;
    private String content;
    private Set<Long> assignedUserIds;

    public ScheduleRequestDto() {
    }
}