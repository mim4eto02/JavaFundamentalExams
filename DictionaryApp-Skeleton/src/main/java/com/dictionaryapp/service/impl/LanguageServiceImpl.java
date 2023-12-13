package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.model.mappers.WordMapper;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    private final WordRepository wordRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
    }





    @Override
    public void createDescriptionIfNotExist(LanguageNameEnum languageNameEnum) {
        LanguageEntity languageEntity = languageRepository.findByLanguageName(languageNameEnum);
        if (languageEntity == null) {
            languageEntity = new LanguageEntity();
            languageEntity.setLanguageName(languageNameEnum);

            switch (languageNameEnum) {
                case SPANISH:
                    languageEntity.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                    break;
                case ITALIAN:
                    languageEntity.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                    break;
                case GERMAN:
                    languageEntity.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                    break;
                case FRENCH:
                    languageEntity.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                    break;
            }
            languageRepository.save(languageEntity);
        }
    }

    @Override
    public List<LanguageEntity> getAllLanguages() {
        List<LanguageEntity> languageEntities = languageRepository.findAll();
        languageEntities.sort(Comparator.comparing(LanguageEntity::getLanguageName));
        return languageEntities;
    }

    @Override
    public LanguageEntity getLanguageByName(LanguageNameEnum language) {
        return languageRepository.findByLanguageName(language);
    }
}
