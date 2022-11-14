package com.practicemustach.bbs2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor
@AllArgsConstructor //update는 id도 받기떄문에 추가
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db에다 맞기겠다 autoincrement를
    private long id;
    private String title;
    private String content;

    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }
}
