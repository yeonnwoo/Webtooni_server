package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TalkCommentRepository extends JpaRepository<TalkBoardComment, Long> {

  @Query("select tc from TalkBoardComment tc where tc.talkPost.id=:id")
  List<TalkBoardComment> findAllCommentByBoardId(@Param("id") Long id);

  void deleteAllByTalkPost(TalkPost talkPost);

}
