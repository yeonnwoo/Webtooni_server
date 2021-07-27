package com.sparta.webtooniverse.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Getter
public class LoginRequestDto {
    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String userName;
    @NotBlank(message = "PW는 필수 입력 값입니다.")
    private String password;

    public LoginRequestDto (String userName, String password){}
}
