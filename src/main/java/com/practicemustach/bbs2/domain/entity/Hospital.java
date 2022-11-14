package com.practicemustach.bbs2.domain.entity;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals") //클래스 이름의 테이블이 아닌 태그한 이름의 테이블이라는 표시
@Getter
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address") // 컬럼 이름을 특정
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    //int = 자바에서 Integer이기 때문에 Integer로 맴핑.
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

//    public static HospitalResponse of(Hospital hospital) {
//        return new HospitalResponse(hospital.getId(), hospital.getHospitalName(), hospital.getRoadNameAddress());
//    }

}
