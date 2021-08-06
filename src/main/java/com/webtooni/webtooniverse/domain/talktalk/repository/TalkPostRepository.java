package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostGetRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkPostRepository extends JpaRepository<TalkPost, Long> {
}
