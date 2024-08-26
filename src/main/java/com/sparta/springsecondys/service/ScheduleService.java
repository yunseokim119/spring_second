package com.sparta.springsecondys.service;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.repository.ScheduleRepository;
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

    // 페이징 처리를 포함한 일정 목록 조회
    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedDate").descending());
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(ScheduleResponseDto::new);
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

    public void deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new RuntimeException("일치하는 일정이 없습니다. ID: " + id);
        }
    }
}
