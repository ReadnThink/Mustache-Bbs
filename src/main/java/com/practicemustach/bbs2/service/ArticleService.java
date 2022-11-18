package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.ArticleAddRequest;
import com.practicemustach.bbs2.domain.dto.ArticleAddResponse;
import com.practicemustach.bbs2.domain.dto.ArticleDto;
import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.entity.Article;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.repository.ArticleRepository;
import com.practicemustach.bbs2.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ArticleService {

    //Service가 직접 Repository와 연결되어 Controller는 Repository를 사용하지 않고 Service만 사용하도록 한다.
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public ArticleDto getArticleDto(Long id){
        //findById = Optional을 리턴한다.
        Optional<Article> optionalArticle = articleRepository.findById(id); //Entity -> Article of메소드를 이용해 Dto를 설정
        Article article = optionalArticle.get();
        ArticleDto articleDto = Article.of(article);
        return articleDto;
    }

    public ArticleAddResponse add(ArticleAddRequest dto) {
        Article article = dto.toEntity(); //요청받은 Article을 Entity에 옮긴다. (id값이 없는상태)
        Article savedArticle = articleRepository.save(article); //db에 저장 (Entity가 저장될때 id값 생성된다.)
        return new ArticleAddResponse(savedArticle.getId(),
                savedArticle.getTitle(),savedArticle.getContent());
    }
}