package com.bonappetit.service.impl;

import com.bonappetit.model.dtos.AddRecipeDto;
import com.bonappetit.model.dtos.RecipeDto;
import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.model.enums.CategoryEnum;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements com.bonappetit.service.RecipeService {

    private final RecipeRepository recipeRepository;

    private final UserRepository userRepository;

    private final CategoryService categoryService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, CategoryService categoryService) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void addRecipe(AddRecipeDto addRecipeDto) {
        RecipeEntity recipeEntity = com.bonappetit.model.mappers.RecipeMapper.addRecipeDtoToRecipeEntity(addRecipeDto);
        String username = com.bonappetit.security.SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        recipeEntity.setAddedBy(user);
        recipeEntity.setCategory(categoryService.getCategoryByName(addRecipeDto.getCategory()));

        recipeRepository.save(recipeEntity);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public List<RecipeDto> getRecipesByCategory(CategoryEnum category) {
        return recipeRepository.findAllByCategory_Name(category).stream().map(com.bonappetit.model.mappers.RecipeMapper::reciDtoToRecipeEntity).toList();
    }

    @Override
    public List<RecipeDto> getFavouriteRecipesByUser() {
        String username = com.bonappetit.security.SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        return recipeRepository.findAllByFavouriteBy(user).stream().map(com.bonappetit.model.mappers.RecipeMapper::reciDtoToRecipeEntity).toList();
    }

    @Override
    @Transactional
    public void addRecipeToFavourite(Long id) {
        String username = com.bonappetit.security.SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        RecipeEntity recipe = recipeRepository.findById(id).orElseThrow();
        if (recipe.getFavouriteBy().contains(user)) {
            System.out.println("Already added to favourite");
            return;
        }
        recipe.getFavouriteBy().add(user);
        recipeRepository.save(recipe);
    }

    @Override
    public void initRecipes() {
        if (recipeRepository.count() == 0) {
            RecipeEntity recipeEntity1 = RecipeEntity.builder()
                    .name("Kaiserschmarren")
                    .ingredients("Flour, Egg, Sugar, Milk, Butter")
                    .category(categoryService.getCategoryByName(CategoryEnum.DESSERT))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity2 = RecipeEntity.builder()
                    .name("Souffle")
                    .ingredients("Cream, egg white, butter, milk, egg yolks")
                    .category(categoryService.getCategoryByName(CategoryEnum.DESSERT))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity3 = RecipeEntity.builder()
                    .name("Creme Brulee")
                    .ingredients("Cream, sugar, egg or egg yolks, vanilla")
                    .category(categoryService.getCategoryByName(CategoryEnum.DESSERT))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity4 = RecipeEntity.builder()
                    .name("Cheesecake")
                    .ingredients("Cream cheese, sour cream, graham cracker crumbs, lemon juice, vanilla extract")
                    .category(categoryService.getCategoryByName(CategoryEnum.DESSERT))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity5 = RecipeEntity.builder()
                    .name("Whiskey Sour")
                    .ingredients("Bourbon, simple syrup, egg white, lemon juice, angostura bitters")
                    .category(categoryService.getCategoryByName(CategoryEnum.COCKTAIL))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity6 = RecipeEntity.builder()
                    .name("Mojito")
                    .ingredients("Club soda, simple syrup, ice, white rum, lime juice")
                    .category(categoryService.getCategoryByName(CategoryEnum.COCKTAIL))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity7 = RecipeEntity.builder()
                    .name("Margarita")
                    .ingredients("Triple sec, ice cube, lime juice, sea salt, white tequila")
                    .category(categoryService.getCategoryByName(CategoryEnum.COCKTAIL))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity8 = RecipeEntity.builder()
                    .name("Goulash")
                    .ingredients("Meat, stock, noodles, vegetables (especially potatoes), paprika, spices")
                    .category(categoryService.getCategoryByName(CategoryEnum.MAIN_DISH))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            RecipeEntity recipeEntity9 = RecipeEntity.builder()
                    .name("Moussaka")
                    .ingredients("Eggplant or potatoes, minced meat")
                    .category(categoryService.getCategoryByName(CategoryEnum.MAIN_DISH))
                    .addedBy(userRepository.findByUsername("admin"))
                    .build();

            recipeRepository.saveAll(List.of(recipeEntity1, recipeEntity2, recipeEntity3, recipeEntity4, recipeEntity5, recipeEntity6, recipeEntity7, recipeEntity8, recipeEntity9));
        }
    }

    @Override
    @Transactional
    public void initFavoriteRecipes() {
        recipeRepository.findById(1L).ifPresent(recipeEntity -> {
            UserEntity user = userRepository.findByUsername("admin");
            recipeEntity.getFavouriteBy().add(user);
            recipeRepository.save(recipeEntity);
        });
    }
}
