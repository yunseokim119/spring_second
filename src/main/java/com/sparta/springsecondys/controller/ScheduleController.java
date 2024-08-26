package com.sparta.springsecondys.controller;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private  ScheduleService scheduleService;


    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto savedSchedule = scheduleService.saveSchedule(requestDto);
        return ResponseEntity.ok(savedSchedule);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        try {
            ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, requestDto);
            return ResponseEntity.ok(updatedSchedule);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 일정 페이징 조회
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ScheduleResponseDto> schedulePage = scheduleService.getAllSchedules(pageable);
        return ResponseEntity.ok(schedulePage);
    }
}
