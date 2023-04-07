package ru.practicum.main_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_server.model.enums.CommentStateAdmin;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusAdminComments {

    private String commentAdmin;
    @NotNull
    private CommentStateAdmin stateAdmin;
}
