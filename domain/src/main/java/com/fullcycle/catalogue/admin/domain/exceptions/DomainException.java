package com.fullcycle.catalogue.admin.domain.exceptions;

import com.fullcycle.catalogue.admin.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceExceptions{


    private final List<Error> errors;

    public DomainException(final String aMessage, final List<Error> errors) {
        super(aMessage);
        this.errors = errors;
    }
    public static DomainException with(final Error anError){
        return new DomainException(anError.message(), List.of(anError));
    }

    public static DomainException with(final List<Error> anErrors){
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors(){
        return errors;
    }
}

