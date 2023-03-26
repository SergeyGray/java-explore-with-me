package ru.practicum.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.practicum.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StatsClient {
    private final WebClient webClient;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public HitDto create(HitDto hitDto) {
        return webClient
                .post()
                .uri("http://localhost:9090/hit")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(hitDto), HitDto.class)
                .retrieve()
                .bodyToMono(HitDto.class)
                .block();
    }

    public List<StatsDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("http://localhost:9090/stats")
                        .queryParam("start", start.format(FORMATTER))
                        .queryParam("end", end.format(FORMATTER))
                        .queryParam("uris", uris)
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .bodyToMono(StatsDto[].class)
                .block()));
    }
}
