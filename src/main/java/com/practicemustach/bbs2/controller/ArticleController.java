package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.Article;
import com.practicemustach.bbs2.domain.dto.ArticleDto;
import com.practicemustach.bbs2.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    // Spring이 ArticleRepository구현체를 DI (인터페이스 아님)
    // 인터페이스지만 그 구현체(ArticleDao)를 넣어준다
    // 기능 1. findAll(), findById(),.save()등이 자동으로 만들어집니다.
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles",articles);
        return "articles/list";
    }
    @GetMapping("") //기본페이지도  list로 한다.
    public String index(){
        return "redirect:/articles/list";
    }

    @GetMapping("/new")
    public String createPage(){
        return "articles/new"; //templates에있는 mustache파일을 찾아간다.
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);

        if (!optArticle.isEmpty()){
            // Optional.get을 해서 -> article에 넣어준다??
            model.addAttribute("article",optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()){
            // Optional.get을 해서 -> article에 넣어준다??
            model.addAttribute("article",optArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message",String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }

    @PostMapping("/posts")
    public String add(ArticleDto articleDto){
        log.info("new를 통해 들어온 값은 title = :"+articleDto.getTitle() + "   content = " + articleDto.getContent());
        Article saveArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId:{}", saveArticle.getId());

        return String.format("redirect:/articles/%d", saveArticle.getId()); //get id로 들어간다.
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model){
        log.info("title: {}  content: {}",articleDto.toEntity(),articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity(id));
        //Duplicated Entry에러 나지 않는 이유
        //.save를 할 때 id가 있다면 insert대신 update가 실행되기 때문
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }
}
