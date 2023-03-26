package ru.practicum.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Builder
@Value
@RequiredArgsConstructor
public class StatsDto {

    String app;
    String uri;
    Long hits;

}

