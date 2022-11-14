package com.practicemustach.bbs2.domain.entity;

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
}
