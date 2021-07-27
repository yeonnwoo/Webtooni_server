package com.sparta.webtooniverse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SignupRequestDto {

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String userName;

    @NotBlank
    private String userEmail;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    private String password;
    private String passwordChecker;


    @NotBlank
    private String userImg;

    public SignupRequestDto(String username,  String userEmail, String password, String passwordChecker, String userImg){}
}

