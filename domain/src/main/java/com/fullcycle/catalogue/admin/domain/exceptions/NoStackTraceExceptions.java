package com.fullcycle.catalogue.admin.domain.exceptions;

public class NoStackTraceExceptions extends RuntimeException{

    public NoStackTraceExceptions(final String message) {
        super(message);
    }

    public NoStackTraceExceptions(final String message, final Throwable cause) {
        super(message, cause,true, false);
    }
}
