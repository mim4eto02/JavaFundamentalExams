package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;




    @Data
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class WordDto {

        private Long id;

        private String term;

        private String translation;

        private String example;


        private LocalDate inputDate;


        private LanguageEntity language;

        private UserEntity addedBy;




    }


