package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkLikeController {

  private final TalkLikeService talkLikeService;


  /**
   * 게시글에 좋아요를 누른다.
   *
   * @param id postId
   * @return TalkResponseDto
   */
  @PostMapping("talk/{id}/like")
  public TalkResponseDto like(@PathVariable Long id) {
    return talkLikeService.postLike(id);
  }

}
