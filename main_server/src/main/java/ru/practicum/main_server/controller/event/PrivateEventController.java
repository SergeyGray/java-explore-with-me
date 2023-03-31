package ru.practicum.main_server.controller.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.*;
import ru.practicum.main_server.mapper.EventMapper;
import ru.practicum.main_server.mapper.RequestMapper;
import ru.practicum.main_server.model.Request;
import ru.practicum.main_server.model.User;
import ru.practicum.main_server.service.event_service.EventService;
import ru.practicum.main_server.service.user_service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {
    private final EventService eventService;
    private final UserService userService;

    @GetMapping
    public List<EventShortDto> getEventsUserId(@PathVariable int userId,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                               @RequestParam(defaultValue = "10") @Positive int size,
                                               HttpServletRequest request) {
        userService.getById(userId);
        return eventService.getEventsUserId(userId, from, size, request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(@RequestBody @Valid NewEventDto newEventDto, @PathVariable int userId) {
        User user = userService.getById(userId);
        log.info("service started");
        return eventService.create(EventMapper.toEvent(newEventDto, user));
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByIdUserWithId(@PathVariable int userId, @PathVariable int eventId) {
        userService.getById(userId);
        eventService.getById(eventId);
        return eventService.getEventByIdUserWithId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateUserRequest(@RequestBody @Valid UpdateEventUserRequest updateEventUserRequest,
                                          @PathVariable int userId, @PathVariable int eventId) {
        userService.getById(userId);
        eventService.getById(eventId);
        return eventService.updateUserRequest(updateEventUserRequest, userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsParticipationEventById(@PathVariable int userId,
                                                                           @PathVariable int eventId) {
        userService.getById(userId);
        eventService.getById(eventId);
        List<Request> requests = eventService.getRequestsParticipationEventById(userId, eventId);
        return RequestMapper.toListParticipationRequestDto(requests);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateStatusRequestEventById(
            @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            EventRequestStatusUpdateRequest updateRequest,
            @PathVariable int userId, @PathVariable int eventId) {
        userService.getById(userId);
        eventService.getById(eventId);
        return eventService.updateStatusRequestEventById(updateRequest, userId, eventId);
    }
}
