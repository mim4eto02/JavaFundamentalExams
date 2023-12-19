package com.bonappetit.init;

import com.bonappetit.model.enums.CategoryEnum;
import com.bonappetit.service.CategoryService;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateBaseInit {

    private final CategoryService categoryService;

    private final UserService userService;

    private final RecipeService recipeService;

    public DateBaseInit(CategoryService categoryService, UserService userService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (categoryService.getAllCategories().isEmpty()) {
                categoryService.createCategory(CategoryEnum.COCKTAIL);
                categoryService.createCategory(CategoryEnum.DESSERT);
                categoryService.createCategory(CategoryEnum.MAIN_DISH);
            }
            userService.initAdmin();
            recipeService.initRecipes();
            recipeService.initFavoriteRecipes();

        };
    }
}
