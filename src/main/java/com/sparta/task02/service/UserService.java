package com.sparta.task02.service;


import com.sparta.task02.dto.SignupRequestDto;
import com.sparta.task02.model.User;
import com.sparta.task02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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


    public int isDuplicateName(String nickname){
        Optional<User> found = userRepository.findByUsername(nickname);
        if (found.isPresent()){
            return 1;
        } else {
            return 0;
        }
    }


    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String inputpassword = requestDto.getPassword();
        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//        }
        // 패스워드 암호화
        if (username!=null && inputpassword !=null){
            String password = passwordEncoder.encode(requestDto.getPassword());
            User user = new User(username, password);
            userRepository.save(user);
        }
//        String password = passwordEncoder.encode(requestDto.getPassword());
//        User user = new User(username, password);
//        userRepository.save(user);
    }


}
