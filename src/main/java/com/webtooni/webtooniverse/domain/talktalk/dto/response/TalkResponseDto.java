package com.webtooni.webtooniverse.domain.talktalk.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TalkResponseDto {

  private String result;

  public TalkResponseDto(String result) {
    this.result = result;
  }
}
