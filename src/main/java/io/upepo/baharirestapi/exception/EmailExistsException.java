package io.upepo.baharirestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message){ super(message);}
}
