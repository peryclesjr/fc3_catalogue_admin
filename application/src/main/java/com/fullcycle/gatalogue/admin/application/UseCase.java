package com.fullcycle.gatalogue.admin.application;


import com.fullcycle.catalogue.admin.domain.category.Category;

public class UseCase {

    public Category execute(){
        return Category.newCategory(null, null, false);
    }
}
