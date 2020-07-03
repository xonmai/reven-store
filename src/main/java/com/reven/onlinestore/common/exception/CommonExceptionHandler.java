package com.reven.onlinestore.common.exception;

import com.reven.onlinestore.common.model.ErrorMessage;
import com.reven.onlinestore.common.model.SystemErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    private ResponseEntity<ErrorMessage> handleEntityNotFound(Throwable ex) {
        return respondException(ex, SystemErrorCode.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<ErrorMessage> handleException(Throwable ex) {
        return respondException(ex, SystemErrorCode.UNKNOWN_SYSTEM_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> respondException(Throwable ex, SystemErrorCode sysCode, HttpStatus status) {
        log.error("Exception in system:", ex);
        return new ResponseEntity(
                new ErrorMessage(
                        sysCode.getName(),
                        ex.getMessage()
                ),
                null,
                status
        );
    }
}
