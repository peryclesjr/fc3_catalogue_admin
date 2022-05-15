package com.fullcycle.catalogue.admin.domain.category;

import com.fullcycle.catalogue.admin.domain.Identifier;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {
    protected final String value;

    private CategoryID(String value) {
        this.value = value;
    }

    public static CategoryID unique(){
        return CategoryID.from(UUID.randomUUID());
    }

    public static CategoryID from(final String anId){
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID anId){
        return new CategoryID(anId.toString().toLowerCase());
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
