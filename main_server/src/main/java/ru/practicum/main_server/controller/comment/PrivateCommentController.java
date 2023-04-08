package ru.practicum.main_server.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.CommentFullDto;
import ru.practicum.main_server.dto.CommentStatusDto;
import ru.practicum.main_server.dto.NewCommentDto;
import ru.practicum.main_server.dto.UpdateCommentDto;
import ru.practicum.main_server.mapper.CommentMapper;
import ru.practicum.main_server.model.Comment;
import ru.practicum.main_server.service.comment_service.CommentService;
import ru.practicum.main_server.service.event_service.EventService;
import ru.practicum.main_server.service.user_service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/comments")
public class PrivateCommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentFullDto addComment(@RequestBody @Valid NewCommentDto newCommentDto, @PathVariable int userId,
                                     @RequestParam int eventId) {
        userService.getById(userId);
        eventService.getById(eventId);
        Comment comment = commentService.add(newCommentDto, userId, eventId);
        return CommentMapper.toCommentFullDto(comment);
    }

    @PatchMapping("/{commentId}")
    public CommentFullDto update(@RequestBody @Valid UpdateCommentDto updateCommentDto,
                                 @PathVariable int userId, @PathVariable int commentId) {
        userService.getById(userId);
        commentService.getById(commentId);
        Comment comment = commentService.update(updateCommentDto, userId, commentId);
        return CommentMapper.toCommentFullDto(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int userId, @PathVariable int commentId) {
        userService.getById(userId);
        commentService.getById(commentId);
        commentService.delete(userId, commentId);
    }

    @GetMapping("/{commentId}")
    public CommentStatusDto getCommentStatus(@PathVariable int userId, @PathVariable int commentId) {
        userService.getById(userId);
        commentService.getById(commentId);
        return commentService.getCommentStatus(userId, commentId);
    }

    @GetMapping
    public List<CommentFullDto> getComments(@PathVariable int userId,
                                            @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                            @RequestParam(defaultValue = "10") @Positive int size) {
        userService.getById(userId);
        List<Comment> comments = commentService.getComments(userId, from, size);
        return CommentMapper.toListCommentFullDto(comments);
    }
}
