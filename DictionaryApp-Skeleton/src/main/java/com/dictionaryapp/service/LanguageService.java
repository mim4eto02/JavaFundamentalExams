package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;

import java.util.List;

public interface LanguageService {

void createDescriptionIfNotExist(LanguageNameEnum languageNameEnum);


    List<LanguageEntity> getAllLanguages();

    LanguageEntity getLanguageByName(LanguageNameEnum language);
}
