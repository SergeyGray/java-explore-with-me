package ru.practicum.main_server.service.comment_service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_server.dto.CommentStatusDto;
import ru.practicum.main_server.dto.NewCommentDto;
import ru.practicum.main_server.dto.UpdateCommentDto;
import ru.practicum.main_server.dto.UpdateStatusAdminComments;
import ru.practicum.main_server.exception.ConflictException;
import ru.practicum.main_server.exception.NotFoundException;
import ru.practicum.main_server.mapper.CommentMapper;
import ru.practicum.main_server.model.Comment;
import ru.practicum.main_server.model.CommentsAdmin;
import ru.practicum.main_server.model.Event;
import ru.practicum.main_server.model.User;
import ru.practicum.main_server.model.enums.CommentStateAdmin;
import ru.practicum.main_server.model.enums.StatusComment;
import ru.practicum.main_server.repository.CommentRepository;
import ru.practicum.main_server.repository.CommentsAdminRepository;
import ru.practicum.main_server.service.event_service.EventService;
import ru.practicum.main_server.service.user_service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;
    private final CommentsAdminRepository commentsAdminRepository;

    @Override
    @Transactional
    public Comment add(NewCommentDto newCommentDto, int userId, int eventId) {
        User commentator = userService.getById(userId);
        Event event = eventService.getById(eventId);
        if (event.getInitiator().getId() == userId) {
            throw new ConflictException("Нельзя оставлять комментарий на свое событие");
        }
        Comment comment = new Comment();
        comment.setComment(newCommentDto.getComment());
        comment.setCommentator(commentator);
        comment.setCommentedEvent(event);
        comment.setCreateOn(LocalDateTime.now());
        comment.setPublishedOn(null);
        comment.setEditOn(null);
        comment.setStatus(StatusComment.PENDING);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(UpdateCommentDto updateCommentDto, int userId, int commentId) {
        Comment comment = getById(commentId);
        comment.setComment(updateCommentDto.getComment());
        comment.setEditOn(LocalDateTime.now());
        comment.setPublishedOn(null);
        comment.setStatus(StatusComment.PENDING);
        return comment;
    }

    @Override
    @Transactional
    public void delete(int userId, int commentId) {
        Comment comment = getById(commentId);
        if (comment.getCommentator().getId() != userId) {
            throw new ConflictException("Можно удалить только свой комментарий");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment getById(int commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Комментарий не найден"));
    }

    @Override
    @Transactional
    public Comment updateCommentStatus(UpdateStatusAdminComments updateStatusAdminComments, int commentId) {
        Comment comment = getById(commentId);
        if (updateStatusAdminComments.getStateAdmin().equals(CommentStateAdmin.PUBLISH_COMMENT)) {
            comment.setPublishedOn(LocalDateTime.now());
            comment.setStatus(StatusComment.PUBLISHED);
        }
        if (updateStatusAdminComments.getStateAdmin().equals(CommentStateAdmin.REJECT_COMMENT)) {
            comment.setStatus(StatusComment.REJECTED);
            CommentsAdmin commentsAdmin = new CommentsAdmin();
            commentsAdmin.setCommentId(comment);
            commentsAdmin.setCommentAdmin(updateStatusAdminComments.getCommentAdmin());
            commentsAdmin.setCreatedOn(LocalDateTime.now());
            commentsAdminRepository.save(commentsAdmin);
        }
        return comment;
    }

    @Override
    public CommentStatusDto getCommentStatus(int userId, int commentId) {
        Comment comment = commentRepository.getCommentById(commentId, userId);
        CommentsAdmin commentsAdmin;
        if (comment.getStatus().equals(StatusComment.REJECTED)) {
            commentsAdmin = commentsAdminRepository.getCommentAdmin(commentId);
            return CommentMapper.toCommentStatusDto(comment, commentsAdmin);
        } else {
            return CommentMapper.toCommentStatusDto(comment, null);
        }
    }

    @Override
    public List<Comment> getComments(int userId, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return commentRepository.getComments(userId, pageable).getContent();
    }
}
