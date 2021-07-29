package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class TalkLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long talkPostId;

    @Column(nullable = false)
    private Long userId;


    public TalkLike(Long talkPostId, Long userId){
        this.talkPostId = talkPostId;
        this.userId = userId;
    }

}
