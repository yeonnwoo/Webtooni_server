package com.webtooni.webtooniverse.domain.talktalk.domain;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "talk_board_like")
public class TalkLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_board_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_post_id")
    private TalkPost talkPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TalkLike(TalkPost talkPost, User user){
        this.talkPost = talkPost;
        this.user = user;
    }

}
