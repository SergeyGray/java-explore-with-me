package ru.practicum.main_server.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Builder
public class NewEventDto {
    @Size(min = 20, max = 1000)
    @NotBlank
    private String annotation;
    @NotNull
    private Integer category;
    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;
    @NotNull
    private String eventDate;
    @Valid
    @NotNull
    private LocationDto location;
    @NotNull
    private Boolean paid = false;
    @PositiveOrZero
    private int participantLimit;
    private boolean requestModeration = true;
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
}
