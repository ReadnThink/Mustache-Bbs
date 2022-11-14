package com.practicemustach.bbs2.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Comment {
    @Id
    private Long id;

    private String comment;
    private String createDate;

    private String userName;
}
