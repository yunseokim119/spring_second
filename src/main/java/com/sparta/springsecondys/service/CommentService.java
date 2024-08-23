package com.sparta.springsecondys.service;

import com.sparta.springsecondys.dto.CommentRequestDto;
import com.sparta.springsecondys.dto.CommentResponseDto;
import com.sparta.springsecondys.entity.Comment;
import com.sparta.springsecondys.entity.Schedule;
import com.sparta.springsecondys.repository.CommentRepository;
import com.sparta.springsecondys.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto saveComment(CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("일정이 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.setContent(requestDto.getContent());
        comment.setUserName(requestDto.getUserName());
        comment.setSchedule(schedule);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setModifiedDate(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    public Optional<CommentResponseDto> getCommentById(Long id) {
        return commentRepository.findById(id).map(CommentResponseDto::new);
    }

    public List<CommentResponseDto> getCommentsByScheduleId(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        return commentRepository.findById(id).map(comment -> {
            comment.setContent(requestDto.getContent());
            comment.setModifiedDate(LocalDateTime.now());
            Comment updatedComment = commentRepository.save(comment);
            return new CommentResponseDto(updatedComment);
        }).orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다. ID: " + id));
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new RuntimeException("댓글이 존재하지 않습니다. ID: " + id);
        }
    }
}
