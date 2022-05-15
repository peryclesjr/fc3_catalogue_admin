package com.fullcycle.catalogue.admin.domain.category;

import com.fullcycle.catalogue.admin.domain.AgregateRoot;
import com.fullcycle.catalogue.admin.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category  extends AgregateRoot<CategoryID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createAt;
    private Instant updateAt;
    private Instant deleteAt;


    private Category(final CategoryID anId,
                     final String aName,
                     final String aDescription,
                     final boolean aActive,
                     final Instant aCreateDate,
                     final Instant anUpdateDate,
                     final Instant aDeleteDate) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = aActive;
        this.createAt = aCreateDate;
        this.updateAt = anUpdateDate;
        this.deleteAt = aDeleteDate;
    }

    public static Category newCategory(final String actualName, final String actualDescription, final boolean isActivate){
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deleteAt = isActivate? null: now;
        return new Category(id, actualName, actualDescription, isActivate, now, now, deleteAt);
    }

    @Override
    public void validate(final ValidationHandler handler){
        new CategoryValidator(this, handler).validate();
    }

    public Category deActivate(){
        if(getDeleteAt() == null){
            this.deleteAt = Instant.now();
        }
        this.updateAt = Instant.now();
        this.active = false;
        return this;
    }

    public Category activate(){
        this.deleteAt = null;
        this.updateAt = Instant.now();
        this.active = true;
        return this;
    }

    public Category update(final String aName,
                                final String aDescription,
                                final boolean isActive){
        this.name = aName;
        this.description = aDescription;

        if( isActive){
            activate();
        }else{
            deActivate();
        }

        this.updateAt = Instant.now();
        return this;

    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Instant getDeleteAt() {
        return deleteAt;
    }
}
