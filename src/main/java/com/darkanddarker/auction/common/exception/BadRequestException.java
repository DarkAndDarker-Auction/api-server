package com.darkanddarker.auction.common.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(final String message) {
        super(message);
    }
}

