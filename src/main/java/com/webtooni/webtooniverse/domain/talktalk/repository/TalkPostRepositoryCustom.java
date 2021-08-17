package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TalkPostRepositoryCustom {

  List<TalkPostPageResponseDto> findAllTalkPost(Pageable pageable);
}
