package ru.practicum.main_server.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    public String getMassage() {
        return super.getMessage();
    }
}
