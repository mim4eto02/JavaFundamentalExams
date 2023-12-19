package com.bonappetit.controller;

import com.bonappetit.model.dtos.AddRecipeDto;
import com.bonappetit.model.entity.CategoryEntity;
import com.bonappetit.model.enums.CategoryEnum;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ModelAttribute("addRecipeDto")
    public void initAddRecipeDtoModel(Model model) {
        model.addAttribute("addRecipeDto", new AddRecipeDto());
    }

    @ModelAttribute("categories")
    public CategoryEnum[] categories() {
        return CategoryEnum.values();
    }

    @GetMapping("/recipes/add")
    public ModelAndView addRecipe(ModelAndView modelAndView) {
        modelAndView.setViewName("recipe-add");
        return modelAndView;
    }

    @PostMapping("/recipes/add")
    public String saveRecipe(@Valid @ModelAttribute("addRecipeDto") AddRecipeDto addRecipeDto, BindingResult result, Model model) {
       if (result.hasErrors()) {
            model.addAttribute("addRecipeDto", addRecipeDto);
           List<CategoryEntity> categories = recipeService.getAllCategories();
            return "recipe-add";
        }

        recipeService.addRecipe(addRecipeDto);
        return "redirect:/home";
    }
}
