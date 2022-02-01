package com.sparta.task02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @NotBlank(message = "아이디를 입력해 주세요!")
    @Size(min = 3,max = 10, message = "아이디는 3자 이상 10자 이하로 입력해 주세요!")
    @Pattern(regexp= "^[a-zA-Z0-9]{3,20}$",message = "알파벳 대소문자, 숫자만 입력 가능합니다!")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요!")
    @Size(min = 4, max = 10, message = "비밀번호는 4자 이상 입력해주세요!")
    private String password;
//    private String email;
}
