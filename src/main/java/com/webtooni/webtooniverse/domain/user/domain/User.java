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
    @Column(name = "userId")
    private Long id;

    private String userName;

    private String userEmail;

    private int userImg;

    //private Enum<userGrade>

}
