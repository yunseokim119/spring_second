package com.sparta.springsecondys.service;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.entity.User;
import com.sparta.springsecondys.repository.ScheduleRepository;
import com.sparta.springsecondys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        User owner = userRepository.findById(scheduleRequestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다. " + scheduleRequestDto.getOwnerId()));
        Schedule schedule = new Schedule();
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContent(scheduleRequestDto.getContent());
        schedule.setOwner(owner);
        schedule.setCreatedDate(LocalDateTime.now());
        schedule.setModifiedDate(LocalDateTime.now());

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule, false);
    }

    public ScheduleResponseDto getScheduleById(Long id, boolean includeAssignedUsers) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. ID: " + id));
        return new ScheduleResponseDto(schedule, includeAssignedUsers);
    }

    public Page<ScheduleResponseDto> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(schedule -> new ScheduleResponseDto(schedule, false));
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updatedScheduleDto) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setTitle(updatedScheduleDto.getTitle());
            schedule.setContent(updatedScheduleDto.getContent());
            schedule.setModifiedDate(LocalDateTime.now());
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return new ScheduleResponseDto(updatedSchedule, false);
        }).orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. " + id));
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. " + id));
        scheduleRepository.delete(schedule);
    }
}
