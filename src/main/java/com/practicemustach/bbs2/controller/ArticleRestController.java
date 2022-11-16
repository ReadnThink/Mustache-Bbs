package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.ArticleAddRequest;
import com.practicemustach.bbs2.domain.dto.ArticleAddResponse;
import com.practicemustach.bbs2.domain.dto.ArticleDto;
import com.practicemustach.bbs2.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //json형태로 반환하겠따는 뜻. @Controller + @Responsebody를 합쳐놓은것이다.
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;


    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id){
        ArticleDto articleDto = articleService.getArticleDto(id);
        return ResponseEntity.ok().body(articleDto);
    }

    @PostMapping("/article")
    public ResponseEntity<ArticleAddResponse> addArticle(ArticleAddRequest dto) {
        ArticleAddResponse response = articleService.add(dto);
        return ResponseEntity.ok().body(response);
    }
}
