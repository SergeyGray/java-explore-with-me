package ru.practicum.main_server.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    private Comment commentId;

    @Column(name = "comment_admin")
    private String commentAdmin;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}

