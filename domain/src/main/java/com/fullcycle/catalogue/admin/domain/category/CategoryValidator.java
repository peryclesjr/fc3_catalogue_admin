package com.fullcycle.catalogue.admin.domain.category;

import com.fullcycle.catalogue.admin.domain.validation.Error;
import com.fullcycle.catalogue.admin.domain.validation.ValidationHandler;
import com.fullcycle.catalogue.admin.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory,  final ValidationHandler aHandler){
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstrains();

    }

    private void checkNameConstrains() {
        final var name = this.category.getName();
        if(name == null){
             this.validationHandler().append(new Error("'name' should not be null"));
             return;
        }

        if(name.isBlank()){
            this.validationHandler().append(new Error("'name' should not be Blank"));
            return;
        }

        final var length = name.trim().length();
        if(  length > 255 || length < 3){
            this.validationHandler().append(new Error("'name' must be between 3 letters and 255 characteres"));
            return;
        }
    }
}
