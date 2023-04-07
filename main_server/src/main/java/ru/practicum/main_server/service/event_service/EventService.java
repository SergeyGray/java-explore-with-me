package ru.practicum.main_server.service.event_service;

import ru.practicum.main_server.dto.*;
import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.Request;
import ru.practicum.main_server.model.enums.EventSortEnum;
import ru.practicum.main_server.model.enums.EventStateEnum;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {
    EventFullDto create(Event event);

    Event getById(int eventId);

    EventFullDto getEventByIdUserWithId(int userId, int eventId);

    EventFullDto updateUserRequest(UpdateEventUserRequest userRequest, int userId, int eventId);

    List<Request> getRequestsParticipationEventById(int userId, int eventId);

    EventRequestStatusUpdateResult updateStatusRequestEventById(EventRequestStatusUpdateRequest updateRequest,
                                                                int userId, int eventId);

    List<EventShortDto> getEventsUserId(int userId, int from, int size, HttpServletRequest request);

    List<EventFullDto> getEventsAdmin(List<Integer> usersId, List<EventStateEnum> states, List<Integer> categories,
                                      LocalDateTime rangeStart, LocalDateTime rangeEnd, int from, int size);

    EventFullDto updateEventByIdAdmin(UpdateEventAdminRequest adminRequest, int eventId);

    List<EventShortDto> getEventsPublic(String text, List<Integer> categories, Boolean paid,
                                        LocalDateTime rangeStart, LocalDateTime rangeEnd, Boolean onlyAvailable,
                                        EventSortEnum sort, int from, int size, HttpServletRequest request);

    EventFullDto getEventByIdPublic(int id, HttpServletRequest request);

    Set<Event> getEventForAdmin(Set<Integer> ids);
}
