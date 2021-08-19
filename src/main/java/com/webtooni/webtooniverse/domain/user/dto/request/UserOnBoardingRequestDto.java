package com.webtooni.webtooniverse.domain.user.dto.request;


import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UserOnBoardingRequestDto {

    private ArrayList<String> genres;
    private int userImg;
    private String userName;
}
