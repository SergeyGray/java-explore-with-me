package ru.practicum.service;

import ru.practicum.dto.*;

import java.util.List;

public interface StatsService {

    void createHit(HitDto hitDto);

    List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique);
}
