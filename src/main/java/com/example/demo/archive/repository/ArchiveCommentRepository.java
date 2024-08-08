package com.example.demo.archive.repository;

import com.example.demo.common.entity.Archive;
import com.example.demo.common.entity.ArchiveComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveCommentRepository extends JpaRepository<ArchiveComment, Long> {
    List<ArchiveComment> findByArchiveAndParentIsNull(@Param("archive") Archive archive);
}