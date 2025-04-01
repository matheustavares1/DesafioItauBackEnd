package com.DesafioItau.DesafioBackEndItau.exceptions;

import com.DesafioItau.DesafioBackEndItau.exceptions.excep.TransactionError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class CustomAdviceException {


    StandardError err = new StandardError();

    @ExceptionHandler(TransactionError.class)
    public ResponseEntity<StandardError> transactionError(TransactionError ex, HttpServletRequest request) {
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        err.setMessage(ex.getMessage());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());

        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
