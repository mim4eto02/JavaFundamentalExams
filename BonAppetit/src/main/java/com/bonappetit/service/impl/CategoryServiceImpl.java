package com.bonappetit.service.impl;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.enums.CategoryEnum;
import com.bonappetit.repo.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements com.bonappetit.service.CategoryService{

    private final com.bonappetit.repo.CategoryRepository categoryRepository;

    private final RecipeRepository recipeRepository;

    public CategoryServiceImpl(com.bonappetit.repo.CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void createCategory(CategoryEnum categoryEnum) {
        CategoryEntity categoryEntity = categoryRepository.findByName(categoryEnum);
        if (categoryEntity == null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryEnum);
            switch (categoryEnum) {
                case MAIN_DISH:
                    categoryEntity.setDescription("Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                    break;
                case DESSERT:
                    categoryEntity.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                    break;
                case COCKTAIL:
                    categoryEntity.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                    break;
            }
            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        categories.sort(Comparator.comparing(CategoryEntity::getName));
        return categories;
    }

    @Override
    public CategoryEntity getCategoryByName(CategoryEnum category) {
        return categoryRepository.findByName(category);
    }


}
