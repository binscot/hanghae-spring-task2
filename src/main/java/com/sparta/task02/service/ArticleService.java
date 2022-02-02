package com.sparta.task02.service;


import com.sparta.task02.dto.ArticleRequestDto;
import com.sparta.task02.model.Article;
import com.sparta.task02.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional // 이게 디비에진짜 반영이 필요해! 알려주기
    public void update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
    }

    //게시글 검색
    @Transactional
    public List<Article> search(String keyword){
        return articleRepository.findByTitleContaining(keyword);
    }

}
