package com.bonappetit.service;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.enums.CategoryEnum;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryEnum categoryEnum);

    List<CategoryEntity> getAllCategories();

    CategoryEntity getCategoryByName(CategoryEnum category);

}
