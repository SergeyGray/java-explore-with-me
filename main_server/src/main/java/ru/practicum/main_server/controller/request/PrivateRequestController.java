package ru.practicum.main_server.controller.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.ParticipationRequestDto;
import ru.practicum.main_server.mapper.RequestMapper;
import ru.practicum.main_server.model.Request;
import ru.practicum.main_server.service.request_service.RequestService;
import ru.practicum.main_server.service.user_service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class PrivateRequestController {

    private final RequestService requestService;
    private final UserService userService;

    @GetMapping
    public List<ParticipationRequestDto> getParticipationRequestUserId(@PathVariable int userId) {

        userService.getById(userId);
        List<Request> requests = requestService.getParticipationRequestUserId(userId);
        return RequestMapper.toListParticipationRequestDto(requests);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createParticipationRequest(@PathVariable int userId, @RequestParam int eventId) {

        return RequestMapper.toParticipationRequestDto(requestService.createParticipationRequest(userId, eventId));
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelParticipationRequestId(@PathVariable int userId,
                                                                @PathVariable int requestId) {
        userService.getById(userId);
        requestService.getById(requestId);
        return RequestMapper.toParticipationRequestDto(requestService.cancelParticipationRequestId(userId, requestId));
    }
}
