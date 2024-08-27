package com.sparta.springsecondys.dto;

import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String ownerName;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Set<Long> assignedUserIds;

    public ScheduleResponseDto() {
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.ownerName = schedule.getOwner().getUsername();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
        this.assignedUserIds = schedule.getAssignedUsers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }
}
