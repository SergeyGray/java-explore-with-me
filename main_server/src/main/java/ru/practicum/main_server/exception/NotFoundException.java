package ru.practicum.main_server.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public String getMassage() {
        return super.getMessage();
    }
}
