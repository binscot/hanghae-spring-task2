package com.sparta.task02.controller;

import com.sparta.task02.dto.ArticleRequestDto;
import com.sparta.task02.dto.CommentRequestDto;
import com.sparta.task02.model.Article;
import com.sparta.task02.model.Comment;
import com.sparta.task02.repository.ArticleRepository;
import com.sparta.task02.repository.CommentRepository;
import com.sparta.task02.security.UserDetailsImpl;
import com.sparta.task02.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final ArticleRepository articleRepository;

    @GetMapping("/articles/{id}/comments")
    public List<Comment> getComments(@PathVariable("id") Long articleId) {
        return commentRepository.findByAticleIdOrderByModifiedAtDesc(articleId);
    }



    @PostMapping("/api/articles/{id}/comments")
    public Comment createComment(@RequestBody  CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        String username = userDetails.getUsername();
        Comment comment = new Comment(commentRequestDto,userId, username);
        return commentRepository.save(comment);
    }

    @PutMapping("/api/articles/{articleId}/comments/{commentId}")
    public Long updateComments(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto){
        commentService.updateComment(commentId, requestDto);
        return commentId;
    }




    @DeleteMapping("/api/articles/{articleId}/comments/{commentId}")
    public  String deleteComment(@PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(commentId);
        String name = userDetails.getUser().getUsername();
        String commentUserName = commentRepository.findById(commentId).get().getUsername();
        System.out.println(name);
        System.out.println(commentUserName);
        if (Objects.equals(name, commentUserName)){
            commentRepository.deleteById(commentId);
        } else {
            return "삭제실패";
        }
        return "삭제성공";
    }
}
