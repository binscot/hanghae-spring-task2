package com.sparta.task02.service;


import com.sparta.task02.dto.SignupRequestDto;
import com.sparta.task02.model.User;
import com.sparta.task02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //중복체크 버튼용
    public int isDuplicateName(String nickname){
        Optional<User> found = userRepository.findByUsername(nickname);
        if (found.isPresent()){
            return 1;
        } else {
            return 0;
        }
    }
    //회원가입용 중복체크
    public int nameCheck(SignupRequestDto requestDto){
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()){
            return 1;
        } else {
            return 0;
        }
    }

    //비밀번호에 아이디가 포함되어있는지 체크
    public int namePsswordCheck(SignupRequestDto requestDto){
        String password = requestDto.getPassword();
        String name = requestDto.getUsername();
        if (password.contains(name)){
            return 0;
        } else {
            return 1;
        }
    }

    //회원가입용 비밀번호 일치 체크
    public int checkPassword(SignupRequestDto requestDto){
        String password = requestDto.getPassword();
        String checkPassword = requestDto.getPasswordCheck();
        if (Objects.equals(checkPassword, password)){
            return 2;
        }else {
            return 3;
        }
    }


    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String inputpassword = requestDto.getPassword();
        // 패스워드 암호화
        if (username!=null && inputpassword !=null){
            String password = passwordEncoder.encode(requestDto.getPassword());
            User user = new User(username, password);
            userRepository.save(user);
        }
    }

}
