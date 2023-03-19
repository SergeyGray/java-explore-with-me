package ru.practicum.hit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HitRepository extends JpaRepository <Hit, Integer> {

    @Query("SELECT new ru.practicum.model.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
            "FROM Hit h WHERE h.timeStamp BETWEEN ?1 AND ?2 GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getAllHits(LocalDateTime start, LocalDateTime end);
    @Query("SELECT new ru.practicum.model.StatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) " +
            "FROM Hit h WHERE h.timeStamp BETWEEN ?1 AND ?2 GROUP BY  h.uri, h.app ORDER BY COUNT( h.ip) DESC")
    List<StatsDto> getAllHitsWithoutDuplicated(LocalDateTime star, LocalDateTime end);
    @Query("SELECT new ru.practicum.model.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
            "FROM Hit h WHERE (h.timeStamp BETWEEN ?1 AND ?2) AND (h.uri IN ?3) GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getHits(LocalDateTime start, LocalDateTime end, List<String> uris);
    @Query("SELECT new ru.practicum.model.StatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) " +
            "FROM Hit h WHERE (h.timeStamp BETWEEN ?1 AND ?2) AND (h.uri IN ?3) GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getHitsWithoutDuplicated(LocalDateTime start, LocalDateTime end, List<String> uris);
}
