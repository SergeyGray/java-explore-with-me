package ru.practicum.main_server.service.comment_service;

import ru.practicum.main_server.dto.CommentStatusDto;
import ru.practicum.main_server.dto.NewCommentDto;
import ru.practicum.main_server.dto.UpdateCommentDto;
import ru.practicum.main_server.dto.UpdateStatusAdminComments;
import ru.practicum.main_server.model.Comment;

import java.util.List;

public interface CommentService {

    Comment add(NewCommentDto newCommentDto, int eventId, int userId);

    Comment update(UpdateCommentDto updateCommentDto, int userId, int commentId);

    void delete(int userId, int commentId);

    Comment getById(int commentId);

    Comment updateCommentStatus(UpdateStatusAdminComments updateStatusAdminComments, int commentId);

    CommentStatusDto getCommentStatus(int userId, int commentId);

    List<Comment> getComments(int userId, int from, int size);
}
