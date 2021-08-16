package com.webtooni.webtooniverse.domain.myList;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MyListRequestDto {
    private Long webtoonId;
    private boolean myListOrNot;
}
