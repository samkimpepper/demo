package com.example.demo.common.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public abstract class CollectResponse<T> {
    List<T> collect;
    PaginateResponse meta;
}
