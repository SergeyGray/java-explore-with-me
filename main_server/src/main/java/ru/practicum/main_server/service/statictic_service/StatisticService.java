package ru.practicum.main_server.service.statictic_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.client.StatsClient;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatsClient statsClient;

    public void addHit(HttpServletRequest request) {
        HitDto hitDto = new HitDto();
        hitDto.setApp("ewm-main-service");
        hitDto.setUri(request.getRequestURI());
        hitDto.setIp(request.getRemoteAddr());
        hitDto.setTimestamp(LocalDateTime.now());
        statsClient.create(hitDto);
    }

    public List<StatsDto> getViews(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        return statsClient.get(start, end, uris, unique);

    }
}
