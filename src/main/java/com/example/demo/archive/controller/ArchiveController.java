package com.example.demo.archive.controller;

import com.example.demo.archive.dto.ArchiveCollectResponse;
import com.example.demo.archive.dto.ArchiveResponse;
import com.example.demo.archive.dto.CreateArchiveRequest;
import com.example.demo.archive.service.ArchiveService;
import com.example.demo.common.argumenthandler.Entity;
import com.example.demo.common.entity.Archive;
import com.example.demo.common.service.ReportService;
import com.example.demo.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/archives")
@Tag(name = "아카이브 API")
public class ArchiveController {
    private final ArchiveService service;
    private final ReportService reportService;

    @Operation(summary = "아카이브 생성")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ArchiveResponse> create(
            @AuthenticationPrincipal(errorOnInvalidType = true) User author,
            @RequestPart(value = "request") @Valid CreateArchiveRequest request,
            @RequestPart(required = false) List<MultipartFile> attaches
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(author, request ,attaches));
    }

    @GetMapping
    public ResponseEntity<ArchiveCollectResponse> findNearArchives(
            @Auth User user,
            @RequestParam(value = "x") double currentX,
            @RequestParam(value = "y") double currentY
    ) {
        return ResponseEntity.ok(service.findNearArchives(user, currentX, currentY));
    }
  
    @Operation(summary = "아카이브 신고")
    @ApiResponse(responseCode = "204")
    @PostMapping("{archiveId}/report")
    public ResponseEntity<ArchiveResponse> report(
            @PathVariable(name = "archiveId") long ignoredArchiveId,
            @Entity(name = "archiveId") Archive archive,
            @AuthenticationPrincipal(errorOnInvalidType = true) User user
    ) {
        reportService.report(user, archive);
        return ResponseEntity.noContent().build();
    }
}
