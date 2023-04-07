package ru.practicum.main_server.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.main_server.model.enums.RequestStatusEnum;

import java.util.List;

@Data
@Builder
public class EventRequestStatusUpdateRequest {
    private List<Integer> requestIds;
    private RequestStatusEnum status;
}
