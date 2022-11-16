package com.practicemustach.bbs2.domain.entity;

import com.practicemustach.bbs2.domain.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor //update는 id도 받기때문에 추가
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db에다 맞기겠다 autoincrement를
    private long id;
    private String title;
    private String content;

    //fetch :EAGER = 관계된 Entity정보 미리 불러온다 Lazy = 실제 요청하는 순간 불러온다.
    //mappedBy : 양방향 관계설정 시 관계의 주체가 되는 쪽에서 정의
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") //댓글 정렬
    private List<Reply> replies = new ArrayList<>();

    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
    public Article(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static ArticleDto of(Article article){
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
}
