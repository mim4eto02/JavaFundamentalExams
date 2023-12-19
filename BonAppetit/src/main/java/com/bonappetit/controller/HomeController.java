package com.bonappetit.controller;

import com.bonappetit.model.dtos.RecipeDto;
import com.bonappetit.model.enums.CategoryEnum;
import com.bonappetit.security.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final com.bonappetit.service.RecipeService recipeService;

    public HomeController(com.bonappetit.service.RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index() {
        String username = SecurityUtil.getSessionUser();
        if(!username.equals("anonymousUser")) {

            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<RecipeDto> dessertsList = recipeService.getRecipesByCategory(CategoryEnum.DESSERT);
        List<RecipeDto> cocktailsList = recipeService.getRecipesByCategory(CategoryEnum.COCKTAIL);
        List<RecipeDto> mainDishesList = recipeService.getRecipesByCategory(CategoryEnum.MAIN_DISH);
        List<RecipeDto> favouriteRecipesList = recipeService.getFavouriteRecipesByUser();

        String username = SecurityUtil.getSessionUser();

        model.addAttribute("dessertsList", dessertsList);
        model.addAttribute("cocktailsList", cocktailsList);
        model.addAttribute("mainDishesList", mainDishesList);
        model.addAttribute("favouriteRecipesList", favouriteRecipesList);
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        recipeService.addRecipeToFavourite(id);
        return "redirect:/home";
    }
}
