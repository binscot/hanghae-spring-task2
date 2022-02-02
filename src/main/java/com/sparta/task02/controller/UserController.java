package com.sparta.task02.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.task02.dto.SignupRequestDto;
import com.sparta.task02.repository.UserRepository;
import com.sparta.task02.service.KakaoUserService;
import com.sparta.task02.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    //아이디 중복 체크
    @PostMapping("/api/check")
    public @ResponseBody String checkNick(@RequestBody String nickname){
        System.out.println(nickname);
        int result = userService.isDuplicateName(nickname);
        if (result==1){
            return "중복";
        } else {
            return "중복아님";
        }
    }



    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Validated SignupRequestDto requestDto, BindingResult bindingResult) {
        //리스폰스dto로 넘겨서 ajax로 뿌리기
        String e = String.valueOf(bindingResult.getAllErrors());
        int checkPassword = userService.checkPassword(requestDto);
        System.out.println(checkPassword);
        if (bindingResult.hasErrors()){
            System.out.println(e);
            return "redirect:/user/signup";
        } else if (checkPassword==3){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "redirect:/user/signup";
        } else {
            userService.registerUser(requestDto);
            return "redirect:/user/login";
        }
//        if (!bindingResult.hasErrors() && checkPassword==2){
//            userService.registerUser(requestDto);
//            return "redirect:/user/login";
//        } else {
//            System.out.println(e);
//            return "redirect:/user/signup";
//        }
    }

    //카카오톡 로그인 인가코드받으면 홈으로!
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}