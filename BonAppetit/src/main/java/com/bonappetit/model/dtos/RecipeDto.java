package com.bonappetit.model.dtos;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long id;

    private String name;

    private String ingredients;


    private CategoryEntity category;


    private UserEntity addedBy;


    private Set<UserEntity> favouriteBy = new HashSet<>();
}
