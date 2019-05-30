package io.upepo.baharirestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SystemRecordModifyException extends  Exception {

    public SystemRecordModifyException(String message)
    {
        super(message);
    }
}
