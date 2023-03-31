package ru.practicum.main_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserCreateDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
