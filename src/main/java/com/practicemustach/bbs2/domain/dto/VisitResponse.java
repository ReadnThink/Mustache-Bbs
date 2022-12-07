package com.practicemustach.bbs2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VisitResponse {
    //Visit에서 toResponse로직으로 Parse할 것이다.
    private String hospitalName;
    private String userName;
    private String disease;
    private float amount;
}
