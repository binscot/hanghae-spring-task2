package com.sparta.task02.service;


import com.sparta.task02.dto.ArticleRequestDto;
import com.sparta.task02.model.Article;
import com.sparta.task02.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //상품 전체를 조회
//    public List<Article> getProducts() throws SQLException {
//        List<Article> articles = articleRepository.findAll();
//
//        return articles;
//    }

//    @Autowired
//    public ArticleService(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }
//
//    public Article createArticle(ArticleRequestDto requestDto, Long userId, String userName) {
//        // 요청받은 DTO 로 DB에 저장할 객체 만들기
//        Article article = new Article(requestDto, userId);
//
//        articleRepository.save(article);
//
//        return article;
//    }


    @Transactional // 이게 디비에진짜 반영이 필요해! 알려주기
    public Long update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article.getId();
    }

    //게시글 검색
//    @Transactional
//    public List<Article> search(String keyword){
//        List<Article> articleList = articleRepository.findByTitleContaining(keyword);
//        return articleList;
//    }


}
