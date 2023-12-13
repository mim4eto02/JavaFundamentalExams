package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.security.SecurityUtil;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final WordService wordService;

    public HomeController(WordService wordService) {
        this.wordService = wordService;
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
        List<WordDto> germanWordsList = wordService.getWordsByLanguage(LanguageNameEnum.GERMAN);
        List<WordDto> spanishWordsList = wordService.getWordsByLanguage(LanguageNameEnum.SPANISH);
        List<WordDto> frenchWordsList = wordService.getWordsByLanguage(LanguageNameEnum.FRENCH);
        List<WordDto> italianWordsList = wordService.getWordsByLanguage(LanguageNameEnum.ITALIAN);

        Long allWordsCount = wordService.getAllWordsCount();

        model.addAttribute("germanWordsList", germanWordsList);
        model.addAttribute("spanishWordsList", spanishWordsList);
        model.addAttribute("frenchWordsList", frenchWordsList);
        model.addAttribute("italianWordsList", italianWordsList);
        model.addAttribute("allWordsCount", allWordsCount);

        return "home";

    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {

        wordService.deleteWord(id);

        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {


        wordService.deleteAllWords();

        return "redirect:/home";
    }

}
