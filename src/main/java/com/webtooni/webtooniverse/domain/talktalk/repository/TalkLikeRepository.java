package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkLikeRepository extends JpaRepository<TalkLike, Long> {

    /**
     * TODO exist 사용 성능 확인, 대체 코드 작성
     */
    boolean existsByTalkPostAndUser(TalkPost talkPost, User user);

    void deleteByTalkPostAndUser(TalkPost talkPost, User user);

}
