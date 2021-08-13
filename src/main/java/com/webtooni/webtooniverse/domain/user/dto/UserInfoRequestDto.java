package com.webtooni.webtooniverse.domain.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserInfoRequestDto {
    private int userImg;
    private String userName;
}

