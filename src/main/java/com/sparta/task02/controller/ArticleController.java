package com.sparta.task02.controller;


import com.sparta.task02.dto.ArticleRequestDto;
import com.sparta.task02.model.Article;
import com.sparta.task02.model.Comment;
import com.sparta.task02.model.User;
import com.sparta.task02.repository.ArticleRepository;
import com.sparta.task02.repository.CommentRepository;
import com.sparta.task02.repository.UserRepository;
import com.sparta.task02.security.UserDetailsImpl;
import com.sparta.task02.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;


    //게시글 생성
    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Article article = new Article(requestDto, userId, userDetails.getUsername());
        return articleRepository.save(article);
    }



    //게시글 생성 페이지 이동
    @RequestMapping("/write")
    public ModelAndView write() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("write.html");
        return modelAndView;
    }


    @RequestMapping("/user/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }



    //게시글 전체 조회
    @GetMapping("/api/articles")
    public List<Article> getArticles() {return articleRepository.findAllByOrderByModifiedAtDesc();}

    //게시글 상세페이지 조회
    @GetMapping("/detail/{id}")
    public ModelAndView detailPage(@PathVariable("id") Long Id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Optional<Article> article = articleRepository.findById(Id);
        ModelAndView modelAndView = new ModelAndView("detail.html");
        modelAndView.addObject("id", article.get().getId());
        modelAndView.addObject("title", article.get().getTitle());
        modelAndView.addObject("username", article.get().getUsername());
        modelAndView.addObject("contents", article.get().getContents());
        modelAndView.addObject("createdAt", article.get().getCreatedAt());
        modelAndView.addObject("modifiedAt", article.get().getModifiedAt());
//        if (userDetails.getUser().getUsername() != null){
//            modelAndView.addObject("name",userDetails.getUser().getUsername());
//        }
//        modelAndView.addObject("[[${name}]]",userDetails.getUser().getUsername());
//        modelAndView.addObject("commentName",comment.get().getUsername());
//        String commentname = comment.get().getUsername();
//        System.out.println(commentname);


        return modelAndView;
    }



    //게시글 검색
//    @GetMapping("/api/search")
//    public List<Article> search(String keyword){
//        List<Article> searchList = articleService.search(keyword);
//        return searchList;
//    }



    //게시글 수정
    @PutMapping("/api/articles/{id}")
    public Long updateArticles(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){

        articleService.update(id, requestDto);
        return id;
    }


    //게시글 수정페이지로 원 게시글 정보 가져가기
//    @GetMapping("/edit/{id}")
//    public ModelAndView editPage(@PathVariable("id") Long Id){
//        Optional<Article> article = articleRepository.findById(Id);
//        ModelAndView modelAndViewa = new ModelAndView("edit.html");
//        modelAndViewa.addObject("id", article.get().getId());
//        modelAndViewa.addObject("title", article.get().getTitle());
//        modelAndViewa.addObject("username", article.get().getUsername());
//        modelAndViewa.addObject("contents", article.get().getContents());
//        modelAndViewa.addObject("createdAt", article.get().getCreatedAt());
//        modelAndViewa.addObject("modifiedAt", article.get().getModifiedAt());
//
//        return modelAndViewa;
//
//    }

    //게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public String deleteArticle(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String name = userDetails.getUser().getUsername();
        String AticleUserName = articleRepository.findById(id).get().getUsername();
        if (Objects.equals(name, AticleUserName)){
            articleRepository.deleteById(id);
        } else {
            return "삭제실패";
        }
        return "삭제성공";
    }

}
