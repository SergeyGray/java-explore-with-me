package ru.practicum.main_server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestConfirmedDto {
    Long eventId;
    Long count;
}
