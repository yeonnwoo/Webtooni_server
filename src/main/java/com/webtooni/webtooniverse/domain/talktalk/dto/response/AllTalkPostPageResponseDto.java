package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AllTalkPostPageResponseDto {

  private List<TalkPostPageResponseDto> posts;
  private Long postCount;


  public AllTalkPostPageResponseDto(List<TalkPostPageResponseDto> posts, Long postCount) {
    this.posts = posts;
    this.postCount = postCount;
  }
}
