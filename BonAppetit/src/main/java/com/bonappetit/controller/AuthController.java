package com.bonappetit.controller;

import com.bonappetit.model.dtos.RegistrationDto;
import com.bonappetit.model.entity.UserEntity;
import com.bonappetit.security.SecurityUtil;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        String username = SecurityUtil.getSessionUser();
        if(!username.equals("anonymousUser")) {

            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        String username = SecurityUtil.getSessionUser();
        if(!username.equals("anonymousUser")) {

            return "redirect:/home";
        }
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model) {
        String username = SecurityUtil.getSessionUser();
        if(!username.equals("anonymousUser")) {

            return "redirect:/home";
        }
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login?success";
    }
}