package com.bci.users.controller;

import com.bci.users.dto.ErrorMessage;
import com.bci.users.dto.ResponseApiError;
import com.bci.users.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class UserErrorHandler {
    @ExceptionHandler({UserEmailException.class, UserPasswordException.class,
            UserDuplicatedException.class, UserNotFoundException.class,
            UserTokenNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseApiError  handlerUserExceptions(Exception e){
        ErrorMessage message = ErrorMessage.builder()
                .timestamp((new Date()).getTime())
                .detail(e.getMessage())
                .codigo(HttpStatus.BAD_REQUEST.value())
                .build();
        List<ErrorMessage> error = new ArrayList<>();
        error.add(message);
        ResponseApiError response = new ResponseApiError();
        response.setError(error);
        return response;
    }
}
