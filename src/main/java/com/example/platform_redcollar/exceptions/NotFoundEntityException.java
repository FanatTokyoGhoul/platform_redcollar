package com.example.platform_redcollar.exceptions;


public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(Long id) {
        super("Not found entity by id = " + id);
    }
}
