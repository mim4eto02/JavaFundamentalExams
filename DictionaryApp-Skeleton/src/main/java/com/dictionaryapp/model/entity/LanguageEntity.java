package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNameEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "languages")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageNameEnum languageName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Set<WordEntity> words = new HashSet<>();


}
