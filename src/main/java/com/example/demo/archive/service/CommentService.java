package com.example.demo.archive.service;

import com.example.demo.archive.dto.CommentCollectResponse;
import com.example.demo.archive.dto.CommentDetailResponse;
import com.example.demo.archive.dto.CommentResponse;
import com.example.demo.archive.dto.CreateCommentRequest;
import com.example.demo.archive.repository.ArchiveCommentRepository;
import com.example.demo.common.dto.PaginateResponse;
import com.example.demo.common.entity.Archive;
import com.example.demo.common.entity.ArchiveComment;
import com.example.demo.common.error.EntityNotFoundException;
import com.example.demo.common.error.ErrorCode;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArchiveCommentRepository repository;

    public CommentResponse create(User author, Archive archive, CreateCommentRequest request, ArchiveComment parent) {
        var comment = ArchiveComment.builder()
                .author(author)
                .archive(archive)
                .parent(parent)
                .content(request.getContent())
                .build();

        return CommentResponse.fromEntity(repository.save(comment));
    }

    public CommentCollectResponse get(Archive archive) {
        var comments = repository.findByArchiveAndParentIsNull(archive);
        return CommentCollectResponse.builder()
                .collect(comments.stream().map(CommentResponse::fromEntity).toList())
                .meta(PaginateResponse.builder().count(comments.size()).build())
                .build();
    }

    public CommentDetailResponse find(Archive ignoredArchive, ArchiveComment comment) {
        return CommentDetailResponse.fromEntity(comment);
    }
}
