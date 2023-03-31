package ru.practicum.main_server.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_server.dto.*;
import ru.practicum.main_server.model.Category;
import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.Location;
import ru.practicum.main_server.model.User;
import ru.practicum.main_server.model.enums.EventStateEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class EventMapper {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Event toEvent(NewEventDto newEventDto, User user) {
        LocalDateTime currentTime = LocalDateTime.now();
        String formatDateTime = currentTime.format(FORMATTER);
        return Event.builder()
                .initiator(user)
                .annotation(newEventDto.getAnnotation())
                .category(new Category(newEventDto.getCategory()))
                .description(newEventDto.getDescription())
                .eventDate(LocalDateTime.parse(newEventDto.getEventDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .location(new Location(newEventDto.getLocation().getLat(), newEventDto.getLocation().getLon()))
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.isRequestModeration())
                .title(newEventDto.getTitle())
                .createOn(LocalDateTime.parse(formatDateTime, FORMATTER))
                .publishedOn(null)
                .state(EventStateEnum.PENDING)
                .build();
    }

    public static EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(new CategoryDto(event.getCategory().getId(), event.getCategory().getName()))
                .confirmedRequests(0)
                .createdOn(event.getCreateOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(new UserShortDto(event.getInitiator().getId(), event.getInitiator().getName()))
                .location(LocationDto.builder().lat(event.getLocation().getLat())
                        .lon(event.getLocation().getLon()).build())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(0)
                .build();
    }

    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(new CategoryDto(event.getCategory().getId(), event.getCategory().getName()))
                .confirmedRequests(0)
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(new UserShortDto(event.getInitiator().getId(), event.getInitiator().getName()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(0)
                .build();
    }

    public static List<EventShortDto> toListEventsShortDto(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    public static List<EventShortDto> toSetEventsShortDto(Set<Event> events) {
        return events.stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    public static List<EventFullDto> toListEventFullDto(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toList());
    }
}
