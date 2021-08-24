package com.webtooni.webtooniverse.domain.user.dto.request;


import java.util.ArrayList;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Getter
public class UserOnBoardingRequestDto {

    private ArrayList<String> genres;
    private int userImg;

    @NotBlank
    @Pattern(regexp="^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,8}$",
            message = "ID는 3글자 이상 20글자 이하의 값이어야 합니다.")
    private String userName;

}
