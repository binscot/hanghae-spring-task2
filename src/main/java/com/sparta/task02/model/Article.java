package com.sparta.task02.model;

import com.sparta.task02.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;



    public Article(ArticleRequestDto requestDto, Long userId, String username) {
        // 관심상품을 등록한 회원 Id 저장
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = username;
    }

    public void update(ArticleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
