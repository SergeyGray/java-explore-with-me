package ru.practicum.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hits")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String app;
    @NonNull
    private String uri;
    @NonNull
    private String ip;
    @NonNull
    private LocalDateTime timestamp;

    public Hit(@NonNull String app, @NonNull String uri, @NonNull String ip, @NonNull LocalDateTime timeStamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timestamp = timeStamp;
    }
}
