package com.bonappetit.repo;

import com.bonappetit.model.entity.RecipeEntity;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    List<RecipeEntity> findAllByCategory_Name(CategoryEnum category);

    List<RecipeEntity> findAllByFavouriteBy(UserEntity user);
}


