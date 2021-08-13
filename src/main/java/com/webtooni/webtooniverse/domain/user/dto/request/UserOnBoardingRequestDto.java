package com.webtooni.webtooniverse.domain.user.dto.request;


import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UserOnBoardingRequestDto {
    private ArrayList<String> genres;
    private int userImg;
    private String userName;
}
