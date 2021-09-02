package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkLikeRepository extends JpaRepository<TalkLike, Long> {

    /**
     * 사용자가 작성한 게시글 유무 확인
     * @param talkPost 확인할 게시글 객체
     * @param user 유저 객체
     * @return 게시글 유무
     */
    boolean existsByTalkPostAndUser(TalkPost talkPost, User user);

//    void deleteByTalkPostAndUser(TalkPost talkPost, User user);

    /**
     * 해당 게시글을 삭제합니다.
     * @param talkPost
     */
    void deleteAllByTalkPost(TalkPost talkPost);

    /**
     * 한 게시글에 대해서 유저가 좋아요를 눌렀는지 확인합니다.
     * @param talkPost 확인할 게시글 객체
     * @param user 유저 객체
     * @return TalkLike 객체
     */
    TalkLike findTalkLikeByTalkPostAndUser(TalkPost talkPost,User user);


}
