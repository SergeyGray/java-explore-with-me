package ru.practicum.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.dto.*;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Integer> {

    @Query("SELECT new ru.practicum.dto.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
            "FROM Hit h WHERE h.timeStamp BETWEEN ?1 AND ?2 GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getAllHits(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) " +
            "FROM Hit h WHERE h.timeStamp BETWEEN ?1 AND ?2 GROUP BY  h.uri, h.app ORDER BY COUNT( h.ip) DESC")
    List<StatsDto> getAllHitsWithoutDuplicated(LocalDateTime star, LocalDateTime end);

    @Query("SELECT new ru.practicum.dto.StatsDto(h.app, h.uri, COUNT (h.ip)) " +
            "FROM Hit h WHERE (h.timeStamp BETWEEN ?1 AND ?2) AND (h.uri IN ?3) GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getHits(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.dto.StatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) " +
            "FROM Hit h WHERE (h.timeStamp BETWEEN ?1 AND ?2) AND (h.uri IN ?3) GROUP BY  h.uri, h.app ORDER BY COUNT(h.ip) DESC")
    List<StatsDto> getHitsWithoutDuplicated(LocalDateTime start, LocalDateTime end, List<String> uris);
}
