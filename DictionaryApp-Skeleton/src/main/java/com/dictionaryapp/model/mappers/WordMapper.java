package com.dictionaryapp.model.mappers;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.dto.WordDto;
import com.dictionaryapp.model.entity.WordEntity;
import org.modelmapper.ModelMapper;

public class WordMapper {

    private static final ModelMapper modelMapper = new ModelMapper();


    public static WordEntity addDtotToWordEntity(AddWordDto addWordDto) {
        return modelMapper.map(addWordDto, WordEntity.class);
    }

    public static WordDto wordToWordDto(WordEntity wordEntity) {
        return modelMapper.map(wordEntity, WordDto.class);
    }



}

