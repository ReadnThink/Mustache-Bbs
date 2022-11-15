package com.practicemustach.bbs2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor // hospital에서 parse할때 생성자를 주입받아야 한다.
public class HospitalResponse {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String businessStatusName; //생성자로 주입받지 않고 service를 통해 Entity의 Integer인 businessStatusCode를 String으로 변환하여 setter로 받을 것이다.


    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }
    //변환된 Entity의 businessStatusCode를 받기위해 setter 선언
    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }
}