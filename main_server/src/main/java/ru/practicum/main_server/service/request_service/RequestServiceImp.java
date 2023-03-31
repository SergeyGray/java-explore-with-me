package ru.practicum.main_server.service.request_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_server.exception.ConflictException;
import ru.practicum.main_server.exception.NotFoundException;
import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.Request;
import ru.practicum.main_server.model.User;
import ru.practicum.main_server.model.enums.EventStateEnum;
import ru.practicum.main_server.model.enums.RequestStatusEnum;
import ru.practicum.main_server.repository.EventRepository;
import ru.practicum.main_server.repository.RequestRepository;
import ru.practicum.main_server.service.user_service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImp implements RequestService {
    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserService userService;

    @Override
    public List<Request> getParticipationRequestUserId(int userId) {
        return requestRepository.findByRequesterId(userId);
    }

    @Override
    @Transactional
    public Request createParticipationRequest(int userId, int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Событие не найдено"));
        User requester = userService.getById(userId);
        checkException(event, userId);
        LocalDateTime created = LocalDateTime.now();
        if (event.getRequestModeration().equals(false)) {
            return requestRepository.save(new Request(0, created, event, requester,
                    RequestStatusEnum.CONFIRMED));
        } else {
            return requestRepository.save(new Request(created, event, requester, RequestStatusEnum.PENDING));
        }
    }

    @Override
    @Transactional
    public Request cancelParticipationRequestId(int userId, int requestId) {
        Request request = getById(requestId);
        if (request.getRequester().getId() != userId) {
            throw new NotFoundException("Можно отменить только свой запрос");
        }
        request.setStatus(RequestStatusEnum.CANCELED);
        return request;
    }

    @Override
    public Request getById(int requestId) {
        return requestRepository.findById(requestId).orElseThrow(() ->
                new NotFoundException("Запрос на участие не найден"));
    }

    @Override
    public Integer getConfirmedRequest(int eventId) {
        return requestRepository.getConfirmedRequest(eventId).orElse(0);
    }

    @Override
    public Map<Integer, Integer> getConfirmedRequest(List<Event> events) {
        return requestRepository.getConfirmedRequestsListInteger(events);
    }

    private void checkException(Event event, int userId) {
        int confirmedRequest = getConfirmedRequest(event.getId());
        Request requests = requestRepository.getRequest(event.getId(), userId);
        if (requests != null) {
            throw new ConflictException("Запрос уже был отправлен");
        }
        if (event.getInitiator().getId() == userId) {
            throw new ConflictException("Нельзя отправллять запрос на участие в своем событии");
        }
        if (event.getState().equals(EventStateEnum.PENDING) || event.getState().equals(EventStateEnum.CANCELED)) {
            throw new ConflictException("Нельзя участвовать в неопубликованном событии");
        }
        if (confirmedRequest == event.getParticipantLimit()) {
            throw new ConflictException("Достигнут лимит запросов на участие");
        }
    }
}
