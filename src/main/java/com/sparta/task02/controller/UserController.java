package com.sparta.task02.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.task02.dto.SignupRequestDto;
import com.sparta.task02.service.KakaoUserService;
import com.sparta.task02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
        //작성된 아이디를 서비스로 보내 같은아이디가 있는지 확인 후 1,0 을 반환
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
        //Validated에러를 e값에 넣음
        String e = String.valueOf(bindingResult.getAllErrors());
        //비밀번호 확인
        int checkPassword = userService.checkPassword(requestDto);
        //아이디 중복체크  위에 만들어서 필요없지만 테스트할때 쓰려고 가입단에서 다시한번 체크
        int checkName = userService.nameCheck(requestDto);
        //비밀번호안에 아이디가 있는지 확인
        int checkNamePassword = userService.namePsswordCheck(requestDto);
        //아래 과정을 모두 통과하면 회원가입 성공!
        if (bindingResult.hasErrors()){
            System.out.println(e);
            return "redirect:/user/signup";
        } else if(checkNamePassword==0){
            System.out.println("비밀번호에 아이디를 포함할 수 없습니다.");
            return "redirect:/user/signup";
        } else if (checkPassword==3){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "redirect:/user/signup";
        } else if (checkName==1){
            System.out.println("중복된 아이디 입니다.");
            return "redirect:/user/signup";
        } else {
            userService.registerUser(requestDto);
            return "redirect:/user/login";
        }
    }

    //카카오톡 로그인 인가코드받으면 홈으로!
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}