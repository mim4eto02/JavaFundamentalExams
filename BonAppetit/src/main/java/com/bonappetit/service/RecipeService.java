package com.bonappetit.service;

import com.bonappetit.model.dtos.AddRecipeDto;
import com.bonappetit.model.dtos.RecipeDto;
import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.enums.CategoryEnum;

import java.util.List;

public interface RecipeService {

    void addRecipe(AddRecipeDto addRecipeDto);

    List<CategoryEntity> getAllCategories();

    List<RecipeDto> getRecipesByCategory(CategoryEnum category);

    List<RecipeDto> getFavouriteRecipesByUser();

    void addRecipeToFavourite(Long id);

    void initRecipes();

    void initFavoriteRecipes();
}
