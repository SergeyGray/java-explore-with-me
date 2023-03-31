package ru.practicum.main_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "compilation_events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompilationEvents implements Serializable {
    @Id
    @Column(name = "compilation_id")
    private int compilationId;
    @Id
    @Column(name = "event_id")
    private int eventId;
}
