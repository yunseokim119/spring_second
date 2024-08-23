package com.sparta.springsecondys.controller;

import com.sparta.springsecondys.dto.ScheduleRequestDto;
import com.sparta.springsecondys.dto.ScheduleResponseDto;
import com.sparta.springsecondys.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ScheduleController {

    private  final ScheduleService scheduleService;

    @Autowired
    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

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
}
