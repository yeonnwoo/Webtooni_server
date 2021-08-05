package com.webtoon.demo.domain.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserInfoRequestDto {

    private int userImg;
    private String userName;

}
