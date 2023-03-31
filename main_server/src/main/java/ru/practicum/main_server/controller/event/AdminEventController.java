package ru.practicum.main_server.controller.event;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.EventFullDto;
import ru.practicum.main_server.dto.UpdateEventAdminRequest;
import ru.practicum.main_server.model.enums.EventStateEnum;
import ru.practicum.main_server.service.event_service.EventService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/admin/events")
public class AdminEventController {
    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> getEventsAdmin(@RequestParam(required = false) List<Integer> usersId,
                                             @RequestParam(required = false) List<EventStateEnum> states,
                                             @RequestParam(required = false) List<Integer> categories,
                                             @RequestParam(required = false)
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime rangeStart,
                                             @RequestParam(required = false)
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime rangeEnd,
                                             @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                             @RequestParam(defaultValue = "10") @Positive int size) {
        return eventService.getEventsAdmin(usersId, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByIdAdmin(@RequestBody UpdateEventAdminRequest adminRequest,
                                             @PathVariable int eventId) {
        eventService.getById(eventId);
        return eventService.updateEventByIdAdmin(adminRequest, eventId);
    }
}
