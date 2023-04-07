package ru.practicum.main_server.service.request_service;

import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.Request;

import java.util.List;
import java.util.Map;

public interface RequestService {

    List<Request> getParticipationRequestUserId(int userId);

    Request createParticipationRequest(int userId, int eventId);

    Request cancelParticipationRequestId(int userId, int requestId);

    Request getById(int requestId);

    Integer getConfirmedRequest(int eventId);

    Map<Integer, Integer> getConfirmedRequest(List<Event> events);
}
