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
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 일정 생성
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        User user = userRepository.findById(scheduleRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(user, scheduleRequestDto.getTitle(), scheduleRequestDto.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    // 일정 단건 조회
    public Optional<ScheduleResponseDto> getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(ScheduleResponseDto::new);
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto updatedScheduleDto) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setTitle(updatedScheduleDto.getTitle());
            schedule.setContent(updatedScheduleDto.getContent());
            schedule.setModifiedDate(LocalDateTime.now());
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return new ScheduleResponseDto(updatedSchedule);
        }).orElseThrow(() -> new RuntimeException("일치하는 일정이 없습니다. " + id));
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new RuntimeException("일치하는 일정이 없습니다. ID: " + id);
        }
    }

    // 일정 페이징 조회 (수정일 기준 내림차순 정렬)
    public Page<ScheduleResponseDto> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(ScheduleResponseDto::new);
    }
}
