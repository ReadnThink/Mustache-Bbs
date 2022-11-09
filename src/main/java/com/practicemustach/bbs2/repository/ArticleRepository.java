package com.practicemustach.bbs2.repository;

import com.practicemustach.bbs2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
    // Spring이 ArticleRepository구현체를 DI (인터페이스 아님)
    // 인터페이스지만 그 구현체(ArticleDao)를 넣어준다
    // 기능 1. findAll(), findById(),.save()등이 자동으로 만들어집니다.
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
