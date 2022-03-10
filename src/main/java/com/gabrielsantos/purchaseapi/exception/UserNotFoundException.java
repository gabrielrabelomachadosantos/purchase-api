package com.gabrielsantos.purchaseapi.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ServiceException {

    public UserNotFoundException() {
        super("User not found.");
    }

}
