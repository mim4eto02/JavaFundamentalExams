package com.dictionaryapp.init;

import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.service.LanguageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DataBaseInit {

    private final LanguageService languageService;

    public DataBaseInit(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            if (languageService.getAllLanguages().isEmpty()) {
                languageService.createDescriptionIfNotExist(LanguageNameEnum.FRENCH);
                languageService.createDescriptionIfNotExist(LanguageNameEnum.SPANISH);
                languageService.createDescriptionIfNotExist(LanguageNameEnum.GERMAN);
                languageService.createDescriptionIfNotExist(LanguageNameEnum.ITALIAN);
            }


        };
    }
}





