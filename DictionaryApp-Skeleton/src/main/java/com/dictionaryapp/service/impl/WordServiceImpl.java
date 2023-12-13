package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.model.mappers.WordMapper;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.security.SecurityUtil;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WordServiceImpl implements com.dictionaryapp.service.WordService {

    private final com.dictionaryapp.repo.WordRepository wordRepository;

    private final LanguageService languageService;

    private final UserRepository userRepository;

    public WordServiceImpl(com.dictionaryapp.repo.WordRepository wordRepository, LanguageService languageService, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.languageService = languageService;
        this.userRepository = userRepository;
    }


    @Override
    public void addWord(AddWordDto addWordDto) {
        WordEntity word = WordMapper.addDtotToWordEntity(addWordDto);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        word.setAddedBy(user);
        word.setLanguage(languageService.getLanguageByName(addWordDto.getLanguage()));

        wordRepository.save(word);
    }

    @Override
    public List<LanguageEntity> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @Override
    public LanguageEntity getLanguageByName(LanguageNameEnum language) {
        return languageService.getLanguageByName(language);
    }

    @Override
    public List<WordDto> getWordsByLanguage(LanguageNameEnum language) {
return wordRepository.findAllByLanguage_LanguageName(language).stream().map(WordMapper::wordToWordDto).toList();
    }

    @Override
    public Long getAllWordsCount() {
        return wordRepository.count();
    }

    @Override
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void deleteAllWords() {
        wordRepository.deleteAll();
    }


}
