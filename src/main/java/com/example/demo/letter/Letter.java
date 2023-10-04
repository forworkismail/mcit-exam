package com.example.demo.letter;

import com.example.demo.letter.enums.LetterStatus;
import com.example.demo.letter.enums.LetterType;
import com.example.demo.user.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uniqueIdentifier;

    @Enumerated(EnumType.STRING)
    private LetterType type;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private LetterStatus status;

    // For responses to incoming letters
    @ManyToOne
    @JoinColumn(name = "response_to")
    private Letter responseTo;
}