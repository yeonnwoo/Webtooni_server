package com.webtooni.webtooniverse.domain.myList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MyListRequestDto {

    private Long toonId;
    private boolean myListOrNot;
}
