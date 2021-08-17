package com.webtooni.webtooniverse.domain.user.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
public class UserOnBoardingRequestDto {
    private ArrayList<String> genres;
    private int userImg;
    private String userName;
}
