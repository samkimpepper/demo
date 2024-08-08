package com.example.demo.archive.dto;

import com.example.demo.common.dto.CollectResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@SuperBuilder
@Getter
public class CommentCollectResponse extends CollectResponse<CommentResponse> {
    @JsonProperty("comments")
    List<CommentResponse> collect;
}