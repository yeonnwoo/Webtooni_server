package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkPostRepository extends JpaRepository<TalkPost, Long> {
}
