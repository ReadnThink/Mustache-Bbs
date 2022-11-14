package com.practicemustach.bbs2.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product") //테이블 이름
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number; //상품 번호

    @Column(nullable = false) //not null
    private String name; //이름
    @Column(nullable = false)
    private Integer price; //가격
    @Column(nullable = false)
    private Integer stock; //재고

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
