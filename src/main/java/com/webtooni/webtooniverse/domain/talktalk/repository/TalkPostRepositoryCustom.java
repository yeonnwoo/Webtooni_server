package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TalkPostRepositoryCustom {
    List<TalkPostPageResponseDto> findAllTalkPost(Pageable pageable);
}
