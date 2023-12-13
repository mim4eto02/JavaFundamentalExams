package com.dictionaryapp.repo;

import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, Long> {
    List<WordEntity> findAllByLanguage(LanguageEntity language);

    List<WordEntity> findAllByLanguage_LanguageName(LanguageNameEnum language);
}
