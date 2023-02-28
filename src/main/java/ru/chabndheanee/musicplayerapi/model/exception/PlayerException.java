package ru.chabndheanee.musicplayerapi.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class PlayerException extends RuntimeException {
    public PlayerException(String msg) {
        super(msg);
    }
}
