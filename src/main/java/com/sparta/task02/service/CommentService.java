package com.sparta.task02.service;

import com.sparta.task02.dto.CommentRequestDto;
import com.sparta.task02.model.Comment;
import com.sparta.task02.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.updateComment(requestDto);
    }
}

