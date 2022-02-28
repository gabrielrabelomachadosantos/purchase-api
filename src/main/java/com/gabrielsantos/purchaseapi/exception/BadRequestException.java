package com.gabrielsantos.purchaseapi.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ServiceException {

    public BadRequestException(String message) {
        super(message);
    }

}
