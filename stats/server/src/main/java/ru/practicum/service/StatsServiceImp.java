package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;
import ru.practicum.model.HitMapper;
import ru.practicum.repositoty.StatsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatsServiceImp implements StatsService {

    private final StatsRepository repository;

    @Override
    public void createHit(HitDto hitDto) {
        repository.save(HitMapper.toHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime started = LocalDateTime.parse(start, formatter);
        LocalDateTime ended = LocalDateTime.parse(end, formatter);
        List<StatsDto> stats;
        if (uris == null || uris.isEmpty()) {
            if (!unique) {
                stats = repository.getAllHits(started, ended);
            } else {
                stats = repository.getAllHitsWithoutDuplicated(started, ended);
            }
        } else {
            if (!unique) {
                stats = repository.getHits(started, ended, uris);
            } else {
                stats = repository.getHitsWithoutDuplicated(started, ended, uris);
            }
        }
        return stats;
    }
}
