package com.webtooni.webtooniverse.domain.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserInfoRequestDto {

    private String userImg;
    private String userName;

}

