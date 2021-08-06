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
    @Column(name = "talk_board_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "talk_post_id")
    private TalkPost talkPost;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;


    public TalkLike(TalkPost talkPost, User user){
        this.talkPost = talkPost;
        this.user = user;
    }

}
