package com.sparta.task02.dto;


import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequestDto {
    private String comment;
    //바보같이 여기서 받아버림
    private Long articleId;
}
