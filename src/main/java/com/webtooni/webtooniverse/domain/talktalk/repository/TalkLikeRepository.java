package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkLikeRepository extends JpaRepository<TalkLike, Long> {
    boolean existsByTalkPostIdAndUserId(Long talkPostId, Long userId);
    void deleteByTalkPostIdAndUserId(Long talkPostId, Long userId);
    TalkLike findByTalkPostIdAndUserId(Long talkPostId, Long userId);
}
