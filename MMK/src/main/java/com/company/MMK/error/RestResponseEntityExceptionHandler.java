package com.company.MMK.error;

import com.company.MMK.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParameterMissingException.class)
    public ResponseEntity<ErrorMessage> parameterMissingException(
            ParameterMissingException exception,
            WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                exception.getErrors().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ParameterInvalidException.class)
    public ResponseEntity<ErrorMessage> parameterInvalidException(
            ParameterInvalidException exception,
            WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                exception.getErrors().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ParameterNotFoundException.class)
    public ResponseEntity<ErrorMessage> parameterNotFoundException(
            ParameterNotFoundException exception,
            WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                exception.getError()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(UnexpectedErrorException.class)
    public ResponseEntity<ErrorMessage> unexpectedErrorException(
            UnexpectedErrorException exception,
            WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                exception.getError()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
