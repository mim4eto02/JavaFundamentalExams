package com.dictionaryapp.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "words")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String term;

    @Column(nullable = false, length = 80)
    private String translation;

    @Column(length = 200)
    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity addedBy;
}
