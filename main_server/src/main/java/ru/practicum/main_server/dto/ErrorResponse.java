package ru.practicum.main_server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String reason;
    private final String message;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private final LocalDateTime timestamp;
}
