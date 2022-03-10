package com.gabrielsantos.purchaseapi.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ServiceException {

    public UnauthorizedException() {
        super("Invalid token.");
    }

}
