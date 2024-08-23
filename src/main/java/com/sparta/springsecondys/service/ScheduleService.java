package com.sparta.springsecondys.service;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        schedule.setUserName(requestDto.getUserName());
        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());
        schedule.setCreatedDate(LocalDateTime.now());
        schedule.setModifiedDate(LocalDateTime.now());

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }
    public Optional<ScheduleResponseDto> getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new);
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updatedScheduleDto) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setTitle(updatedScheduleDto.getTitle());
            schedule.setContent(updatedScheduleDto.getContent());
            schedule.setModifiedDate(LocalDateTime.now());
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return new ScheduleResponseDto(updatedSchedule);
        }).orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. " + id));
    }
}
