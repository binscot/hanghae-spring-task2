package com.sparta.task02.repository;


import com.sparta.task02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    //카카오아이디
    Optional<User> findByKakaoId(Long kakaoId);

}
