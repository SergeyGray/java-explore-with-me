package ru.practicum.hit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.HitDto;
import ru.practicum.model.StatsDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class HitServiceImp implements HitService {

    private final HitRepository hitRepository;

    @Override
    public void createHit(HitDto hitDto) {
        hitRepository.save(HitMapper.toHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime started = LocalDateTime.parse(start, formatter);
        LocalDateTime ended = LocalDateTime.parse(end, formatter);
        List<StatsDto> stats;
        if (uris == null || uris.isEmpty()) {
            if (!unique) {
                stats = hitRepository.getAllHits(started, ended);
            } else {
                stats = hitRepository.getAllHitsWithoutDuplicated(started, ended);
            }
        } else {
            if (!unique) {
                stats = hitRepository.getHits(started, ended, uris);
            } else {
                stats = hitRepository.getHitsWithoutDuplicated(started, ended, uris);
            }
        }
        return stats;
    }
}
