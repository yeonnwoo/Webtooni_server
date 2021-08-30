package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TalkCommentRepository extends JpaRepository<TalkBoardComment, Long> {

    /**
     * 게시글에 대한 모든 댓글을 조회합니다.
     * @param id 게시글 id
     * @return 게시글에 대한 댓글 목록
     */
    @Query("select tc from TalkBoardComment tc where tc.talkPost.id=:id")
    List<TalkBoardComment> findAllCommentByBoardId(@Param("id") Long id);

    /**
     * 게시글을 삭제합니다.
     * @param talkPost 삭제할 게시글 객체
     */
    void deleteAllByTalkPost(TalkPost talkPost);

}
