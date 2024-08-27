package com.sparta.springsecondys.service;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.entity.User;
import com.sparta.springsecondys.repository.ScheduleRepository;
import com.sparta.springsecondys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        User owner = userRepository.findById(scheduleRequestDto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다." + scheduleRequestDto.getOwnerId()));

        Schedule schedule = new Schedule();
        schedule.setOwner(owner);
        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setContent(scheduleRequestDto.getContent());
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
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. ID: " + id));

        schedule.setTitle(updatedScheduleDto.getTitle());
        schedule.setContent(updatedScheduleDto.getContent());
        schedule.setModifiedDate(LocalDateTime.now());

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(updatedSchedule);
    }

    public void deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new RuntimeException("일치하는 일정이 없습니다." + id);
        }
    }

    public void assignUserToSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. " + scheduleId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다. " + userId));

        schedule.getAssignedUsers().add(user);
        scheduleRepository.save(schedule);
    }

    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedDate"));
        return scheduleRepository.findAll(pageable).map(ScheduleResponseDto::new);
    }
}
