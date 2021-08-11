package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TalkPostRepository extends JpaRepository<TalkPost, Long> {
    Page<TalkPost> findAllByCreateDateAfterOrderByCreateDateDesc(LocalDateTime localDateTime, Pageable pageable);
}
