package com.practicemustach.bbs2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicemustach.bbs2.domain.dto.ArticleAddRequest;
import com.practicemustach.bbs2.domain.dto.ArticleAddResponse;
import com.practicemustach.bbs2.domain.dto.ArticleDto;
import com.practicemustach.bbs2.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("1개의 Json형태로 Response가 잘 오는지")
    void jsonResponse() throws Exception {
        ArticleDto articleDto = ArticleDto.builder().
                id(1L).
                title("1").
                content("2").build();
        given(articleService.getArticleDto(1L)).willReturn(articleDto);
        Long articleId = 1L;
        String url = String.format("/api/v1/articles/%d", 1L);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").value("2"))
                .andDo(print());

        verify(articleService).getArticleDto(articleId);
    }

    @Test
    @DisplayName("글 등록이 잘 되는지")
    void add() throws Exception {
        //Test만들면서 클래스들을 만든다.
        ArticleAddRequest dto = new ArticleAddRequest("given제목입니다", "given내용입니다");
        //실제 들어갔다고 가정하는 정보
        given(articleService.add(any())).willReturn(new ArticleAddResponse(1L, dto.getTitle(), dto.getContent()));

            mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON) //요청본문설정 (json으로 하겠다는 뜻)
                        .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("content제목입니다","content내용입니다."))) //
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("given내용입니다"))
                .andDo(print());

        verify(articleService).add(any()); //함수가 실행되었는지 확인
    }

}