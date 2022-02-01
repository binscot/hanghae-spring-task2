package com.sparta.task02.repository;


import com.sparta.task02.model.Article;
import com.sparta.task02.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByOrderByModifiedAtDesc();
//
//    List<Comment> findCommentsByAticleId(long aticleId);

    List<Comment> findByAticleIdOrderByModifiedAtDesc(Long aticleId);

//    Optional<Comment> findAllByAticleId(Long Id);


//    List<Comment> findByPostId(Long postId);
//


    //게시글 전체 목록 조회
//    List<Comment> findAllByOrderByModifiedAtDesc();

    //게시글 검색 목록 조회
//    List<Article> findByTitleContaining(String keyword);

}
