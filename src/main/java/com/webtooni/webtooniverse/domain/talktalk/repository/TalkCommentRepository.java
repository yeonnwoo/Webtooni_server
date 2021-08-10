package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkCommentRepository extends JpaRepository<TalkBoardComment, Long> {
    List<TalkBoardComment> findAllById(Long id);
}
