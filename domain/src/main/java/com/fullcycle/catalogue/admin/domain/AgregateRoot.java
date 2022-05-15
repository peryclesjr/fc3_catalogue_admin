package com.fullcycle.catalogue.admin.domain;

import com.fullcycle.catalogue.admin.domain.validation.ValidationHandler;

public class AgregateRoot<ID extends Identifier> extends Entity<ID>{

    protected AgregateRoot(final ID id) {
        super(id);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
