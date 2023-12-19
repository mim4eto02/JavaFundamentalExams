package com.bonappetit.model.mappers;

import com.bonappetit.model.dtos.AddRecipeDto;
import com.bonappetit.model.dtos.RecipeDto;
import com.bonappetit.model.entity.RecipeEntity;
import org.modelmapper.ModelMapper;

public class RecipeMapper {

    private static final ModelMapper modelMapper = new ModelMapper();


    public static RecipeEntity addRecipeDtoToRecipeEntity(AddRecipeDto addRecipeDto) {
        return modelMapper.map(addRecipeDto, RecipeEntity.class);
    }

    public static RecipeDto reciDtoToRecipeEntity(RecipeEntity recipeEntity) {
        return modelMapper.map(recipeEntity, RecipeDto.class);
    }

}
