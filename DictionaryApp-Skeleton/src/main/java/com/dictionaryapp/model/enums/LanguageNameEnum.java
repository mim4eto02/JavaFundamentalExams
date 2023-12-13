package com.dictionaryapp.model.enums;



public enum LanguageNameEnum {
   GERMAN("German", 1),
    SPANISH("Spanish", 2),
    FRENCH("French", 3),
    ITALIAN("Italian", 4);

   private final String languageName;
    private final int id;

    LanguageNameEnum(String name, int id) {
        this.languageName = name;
        this.id = id;
    }

    public String getName() {
        return languageName;
    }

    public int getId() {
        return id;
    }
}
