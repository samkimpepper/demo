package com.example.demo.archive.controller;

import com.example.demo.archive.dto.CommentCollectResponse;
import com.example.demo.archive.dto.CommentDetailResponse;
import com.example.demo.archive.dto.CommentResponse;
import com.example.demo.archive.dto.CreateCommentRequest;
import com.example.demo.archive.service.CommentService;
import com.example.demo.common.argumenthandler.Auth;
import com.example.demo.common.argumenthandler.Entity;
import com.example.demo.common.entity.Archive;
import com.example.demo.common.entity.ArchiveComment;
import com.example.demo.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archives/{archiveId}/comments")
@Tag(name = "댓글 API")
public class CommentController {
    private final CommentService service;

    @Operation(summary = "댓글 작성")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @RequestBody @Valid CreateCommentRequest request,
            @PathVariable(name = "archiveId") long ignoredArchiveId,
            @Entity(name = "archiveId") Archive archive,
            @Auth User author
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(author, archive, request, null));
    }

    @Operation(summary = "대댓글 작성")
    @ApiResponse(responseCode = "201")
    @PostMapping("{commentId}")
    public ResponseEntity<CommentResponse> createSub(
            @RequestBody @Valid CreateCommentRequest request,
            @PathVariable(name = "archiveId") long ignoredArchiveId,
            @Entity(name = "archiveId") Archive archive,
            @PathVariable(name = "commentId") long ignoredCommentId,
            @Entity(name = "commentId") ArchiveComment comment,
            @Auth User author
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(author, archive, request, comment));
    }

    @Operation(summary = "조회")
    @GetMapping
    public ResponseEntity<CommentCollectResponse> get(
            @PathVariable(name = "archiveId") String ignoredArchiveId,
            @Entity(name = "archiveId") @Valid Archive archive,
            @Auth User ignoredAuthor
    ) {
        return ResponseEntity.ok(service.get(archive));
    }

    @Operation(summary = "대댓글 조회")
    @GetMapping("{commentId}")
    public ResponseEntity<CommentDetailResponse> find(
            @PathVariable(name = "archiveId") long ignoredArchiveId,
            @Entity(name = "archiveId") Archive archive,
            @PathVariable(name = "commentId") long ignoredCommentId,
            @Entity(name = "commentId") ArchiveComment comment,
            @Auth User ignoredAuthor
    ) {
        return ResponseEntity.ok(service.find(archive, comment));
    }
}