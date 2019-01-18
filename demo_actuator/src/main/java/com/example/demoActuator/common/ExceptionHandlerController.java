package com.example.demoActuator.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public GenericResponse handleNullPointerExceptions(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage());
        ReturnCodes error = ReturnCodes.E250;
        String fieldName = e.getBindingResult().getFieldError().getField();
        return ResponseBuilder.buildErrorResponse(error.getCode(),error.getTitle(fieldName), error.getTitle(fieldName), null, e.getMessage(), Provider.COMPANY,error.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public GenericResponse handleApplicationExceptions(ApplicationException e) {
        LOGGER.error(e.getMessage());
        return ResponseBuilder.buildErrorResponse(e.getCode(), e.getErrorTitle(),e.getErrorTitle(), e.getKey(), e.getDescription(), Provider.COMPANY,e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public GenericResponse handleNullPointerExceptions(RuntimeException e) {
        LOGGER.error(e.getMessage());
        ReturnCodes error = ReturnCodes.E251;
        return ResponseBuilder.buildErrorResponse(error.getCode(), error.getTitle(), error.getTitle(),
                error.getCode(),e.getMessage(),Provider.COMPANY, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GenericResponse handleDefaultExceptions(Exception e) {
        LOGGER.error(e.getMessage());
        ReturnCodes error = ReturnCodes.E251;
        return ResponseBuilder.buildErrorResponse(error.getCode(),error.getTitle(), error.getTitle(), null, e.getMessage(),
                Provider.COMPANY,error.getMessage());
    }
}
