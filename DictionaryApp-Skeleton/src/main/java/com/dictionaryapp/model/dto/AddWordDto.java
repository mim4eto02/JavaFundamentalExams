package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddWordDto {

    private Long id;

    @Size(min = 2, max = 30, message = "Term must be between 2 and 30 characters.")
    private String term;

    @Size(min = 2, max = 80, message = "Translation must be between 2 and 30 characters.")
    private String translation;

    @Size(min = 2, max = 200, message = "Example must be between 2 and 30 characters.")
    private String example;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent(message = "Date must be in the past or present!")
    private LocalDate inputDate;

    @NotNull(message = "You must select language!")
    private LanguageNameEnum language;

    private UserEntity addedBy;




}
