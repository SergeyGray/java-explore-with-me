package ru.practicum.main_server.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.CommentFullDto;
import ru.practicum.main_server.dto.UpdateStatusAdminComments;
import ru.practicum.main_server.mapper.CommentMapper;
import ru.practicum.main_server.model.Comment;
import ru.practicum.main_server.service.comment_service.CommentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class AdminCommentController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public CommentFullDto updateCommentStatus(@RequestBody @Valid UpdateStatusAdminComments updateStatusAdminComments,
                                              @PathVariable int commentId) {
        commentService.getById(commentId);
        Comment comment = commentService.updateCommentStatus(updateStatusAdminComments, commentId);
        return CommentMapper.toCommentFullDto(comment);
    }
}
