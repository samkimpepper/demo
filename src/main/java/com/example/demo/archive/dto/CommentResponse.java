package com.example.demo.archive.dto;

import com.example.demo.common.entity.ArchiveComment;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class CommentResponse {
    private long id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static CommentResponse fromEntity(ArchiveComment comment) {
        return fromEntity(comment, builder());
    }

    public static <T extends CommentResponse> T fromEntity(ArchiveComment comment,  CommentResponse.CommentResponseBuilder<T, ?> builder) {
        return builder
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}