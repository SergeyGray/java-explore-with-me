package ru.practicum.main_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String name;

    public User(@NonNull String email, @NonNull String name) {
        this.email = email;
        this.name = name;
    }

    public User(int id) {
        this.id = id;
    }
}
