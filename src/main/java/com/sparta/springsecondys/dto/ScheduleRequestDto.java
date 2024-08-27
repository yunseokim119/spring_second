package com.sparta.springsecondys.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long ownerId;
    private String title;
    private String content;
    private Set<Long> assignedUserIds;
}
