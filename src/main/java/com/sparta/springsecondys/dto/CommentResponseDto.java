package com.sparta.springsecondys.dto;

import com.sparta.springsecondys.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private long id;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String username;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.username = comment.getUserName();
    }
}
