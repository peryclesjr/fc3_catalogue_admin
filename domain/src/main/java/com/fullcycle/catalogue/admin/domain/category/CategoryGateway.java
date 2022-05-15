package com.fullcycle.catalogue.admin.domain.category;

import com.fullcycle.catalogue.admin.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {
    Category create(Category aCategory);

    void deleteById(CategoryID anID);

    Optional<Category> findById(CategoryID anID);

    Category update(Category aCategory);

    Pagination<Category> findAll(CategorySearchQuery aQuery);

}
