package com.bonappetit.repo;

import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(CategoryEnum categoryEnum);
}
