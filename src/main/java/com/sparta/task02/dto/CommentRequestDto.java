package com.sparta.task02.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequestDto {
    private String comment;
    private Long articleId;
}
