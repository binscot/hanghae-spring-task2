package com.sparta.task02.model;

import com.sparta.task02.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String comment;

//    @ManyToOne
//    private Article article;
//
//    @ManyToOne
//    private User user;


    @Column(nullable = false)
    private long aticleId;

    @Column(nullable = false)
    private Long userId;


    public Comment(CommentRequestDto commentRequestDto, Long userId, String username) {
        this.userId = userId;
        this.comment = commentRequestDto.getComment();
        this.aticleId = commentRequestDto.getArticleId();
        this.username = username;

    }

    public void updateComment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }


//    public void update(CommentRequestDto requestDto){
//        this.comment = requestDto.getContents();
//    }

}

