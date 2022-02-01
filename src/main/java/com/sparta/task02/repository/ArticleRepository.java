package com.sparta.task02.repository;

import com.sparta.task02.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //게시글 전체 목록 조회
    List<Article> findAllByOrderByModifiedAtDesc();

    //게시글 검색 목록 조회
//    List<Article> findByTitleContaining(String keyword);

}