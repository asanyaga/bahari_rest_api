package io.upepo.baharirestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUserNamePasswordException extends Exception {

    public InvalidUserNamePasswordException(String message)
    {
        super(message);
    }
}
