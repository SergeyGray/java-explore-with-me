package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hits")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @Column(name = "timestamp")
    private LocalDateTime timeStamp;

    public Hit(@NonNull String app, @NonNull String uri, @NonNull String ip, @NonNull LocalDateTime timeStamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timeStamp = timeStamp;
    }
}
