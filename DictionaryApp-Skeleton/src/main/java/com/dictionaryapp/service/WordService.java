package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;

import java.util.List;

public interface WordService {

    void addWord(AddWordDto addWordDto);
    List<LanguageEntity> getAllLanguages();

    LanguageEntity getLanguageByName(LanguageNameEnum language);

    List<WordDto> getWordsByLanguage(LanguageNameEnum language);

    Long getAllWordsCount();

    void deleteWord(Long id);

    void deleteAllWords();
}
