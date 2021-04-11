package com.epam.ems.controllers;

import com.epam.ems.error.ErrorResponse;
import com.epam.ems.exceptions.DaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(DaoException.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String details = ex.getLocalizedMessage();
        ErrorResponse errorResponse = new ErrorResponse("error code =" +INTERNAL_SERVER_ERROR + "12", details);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}
