package com.practicemustach.bbs2.domain.dto;

import com.practicemustach.bbs2.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(this.title, this.content);
    }
    public Article toEntity(Long id){
        return new Article(id, this.title, this.content);
    }
}
