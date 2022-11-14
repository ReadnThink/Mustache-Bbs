package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.ReplyDto;
import com.practicemustach.bbs2.domain.entity.Reply;
import com.practicemustach.bbs2.repository.ArticleRepository;
import com.practicemustach.bbs2.repository.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    public ReplyController(ReplyRepository replyRepository, ArticleRepository articleRepository) {
        this.replyRepository = replyRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/{articleId}")
    public String createReply(@PathVariable Long articleId, ReplyDto replyDto){
        Reply newReply = new Reply();
        newReply.setWriter(replyDto.getWriter());
        newReply.setComment(replyDto.getComment());
        newReply.setArticle(articleRepository.findById(articleId).get());
        replyRepository.save(newReply);
        return "redirect:/articles/" + articleId; //show로 이동
    }

    @GetMapping("/delete/{id}")
    public String deleteReply(@PathVariable Long id) {
        Long articleId = replyRepository.findById(id).get().getArticle().getId();
        replyRepository.deleteById(id);
        return "redirect:/articles/" + articleId;
    }
}
