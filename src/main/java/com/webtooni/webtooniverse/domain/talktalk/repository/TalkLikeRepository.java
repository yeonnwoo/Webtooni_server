package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkLikeRepository extends JpaRepository<TalkLike, Long> {
    boolean existsByTalkPostAndUser(TalkPost talkPost, User user);
    void deleteByTalkPostAndUser(TalkPost talkPost, User user);
    TalkLike findByTalkPostIdAndUserId(Long talkPostId, Long userId);
}
