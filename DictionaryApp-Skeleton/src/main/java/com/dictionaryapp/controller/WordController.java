package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Controller
public class WordController {

    private final WordService wordService;

    private final com.dictionaryapp.service.UserService userService;

    public WordController(WordService wordService, com.dictionaryapp.service.UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @ModelAttribute("languages")
    public LanguageNameEnum[] languages() {
        return LanguageNameEnum.values();
    }

    @ModelAttribute("addWordDTO")
    public void initAddWordDTOModel(Model model) {

        model.addAttribute("addWordDTO", new AddWordDto());
    }

    @GetMapping("/words/new")
    public ModelAndView addWord(ModelAndView modelAndView) {

        modelAndView.setViewName("word-add");

        return modelAndView;
    }

    @PostMapping("/words/new")
    public String saveWord(@Valid @ModelAttribute("addWordDTO") AddWordDto addWordDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("addWordDTO", addWordDTO);
            List<LanguageEntity> languages = wordService.getAllLanguages();
            model.addAttribute("languages", languages);
            return "word-add";
        }
        UserEntity user = userService.findByUsername(com.dictionaryapp.security.SecurityUtil.getSessionUser());
        LanguageEntity selectedLanguage = wordService.getLanguageByName(addWordDTO.getLanguage());

        System.out.println("User: " + user.getUsername());
        System.out.println("Selected Language: " + selectedLanguage.getLanguageName());
        System.out.println("Add Word DTO: " + addWordDTO);

        wordService.addWord(addWordDTO);
        return "redirect:/home";
    }



}
