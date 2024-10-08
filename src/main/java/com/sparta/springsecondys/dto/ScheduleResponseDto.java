package com.sparta.springsecondys.dto;

import com.sparta.springsecondys.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;  // 일정 ID
    private String title;  // 할일 제목
    private String content;  // 할일 내용
    private Long ownerId;  // 일정 작성자의 유저 ID
    private String ownerName;  // 일정 작성자의 유저명
    private LocalDateTime createdDate;  // 일정 생성일
    private LocalDateTime modifiedDate;  // 일정 수정일
    private Set<AssignedUserDto> assignedUsers = new HashSet<>();  // 일정 담당 유저들


    public ScheduleResponseDto() {
    }

    public ScheduleResponseDto(Schedule schedule, boolean includeAssignedUsers) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.ownerId = schedule.getOwner().getId();
        this.ownerName = schedule.getOwner().getUsername();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
        if (includeAssignedUsers) {
            this.assignedUsers = schedule.getScheduleUsers().stream()
                    .map(scheduleUser -> new AssignedUserDto(
                            scheduleUser.getUser().getId(),
                            scheduleUser.getUser().getUsername(),
                            scheduleUser.getUser().getEmail()
                    ))
                    .collect(Collectors.toSet());
        }
    }
}
