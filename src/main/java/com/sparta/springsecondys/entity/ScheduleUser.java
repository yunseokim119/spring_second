package com.sparta.springsecondys.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ScheduleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 중간 엔티티 ID

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;  // 관련 일정

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 관련 사용자

    public ScheduleUser() {
    }

    public ScheduleUser(Long id, Schedule schedule, User user) {
        this.id = id;
        this.schedule = schedule;
        this.user = user;
    }
}
