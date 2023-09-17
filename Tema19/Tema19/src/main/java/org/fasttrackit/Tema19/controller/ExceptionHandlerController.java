package org.fasttrackit.Tema19.controller;

import org.fasttrackit.Tema19.exceptions.ApiException;
import org.fasttrackit.Tema19.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleNotFound(EntityNotFoundException exception) {
        return new ApiException(exception.getMessage(), exception.getEntityId(), HttpStatus.NOT_FOUND);
    }
}
