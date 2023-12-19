package com.bonappetit.model.dtos;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.model.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRecipeDto {

    private Long id;

    @Size(min = 2, max = 40, message = "Recipe name must be between 2 and 40 characters.")
    private String name;

    @Size(min = 2, max = 80, message = "Recipe ingredients must be between 2 and 80 characters.")
    private String ingredients;

    @NotNull(message = "You must select category!")
    private CategoryEnum category;


    private UserEntity addedBy;


    private Set<UserEntity> favouriteBy = new HashSet<>();
}
