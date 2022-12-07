package com.practicemustach.bbs2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisitCreateRequest {
    private int hospitalId;
    private String disease;
    private float amount;
}
