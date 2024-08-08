package com.example.demo.common.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaginateResponse {
    int count;
}
