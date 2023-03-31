package ru.practicum.main_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_server.model.enums.RequestStatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "requester")
    private User requester;
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum status;

    public Request(LocalDateTime created, Event event, User requester, RequestStatusEnum status) {
        this.created = created;
        this.event = event;
        this.requester = requester;
        this.status = status;
    }
}
