package com.fullcycle.catalogue.admin.domain.validation.handler;

import com.fullcycle.catalogue.admin.domain.exceptions.DomainException;
import com.fullcycle.catalogue.admin.domain.validation.Error;
import com.fullcycle.catalogue.admin.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        try{
            aValidation.validate();
        }catch (final Exception ex){
            throw DomainException.with(new Error(ex.getMessage()));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
