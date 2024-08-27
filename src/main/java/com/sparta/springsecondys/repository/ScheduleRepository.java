package com.sparta.springsecondys.repository;

import com.sparta.springsecondys.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
