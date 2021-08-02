package com.webtooni.webtooniverse.domain.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    private String user_name;

    private String user_email;

    private int user_img;

    @Enumerated(EnumType.STRING)
    private User_Grade user_grade;
}
