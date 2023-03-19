package ru.practicum.hit;

import ru.practicum.model.HitDto;
import ru.practicum.model.StatsDto;

import java.util.List;

public interface HitService {

    void createHit(HitDto hitDto);
    List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique);
}
