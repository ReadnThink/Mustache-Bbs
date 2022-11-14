package com.practicemustach.bbs2.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HospitalResponse {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
}