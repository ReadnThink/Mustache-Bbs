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
    private Integer businessStatusCode;

    //HospitalResponse로 클라이언트에게 보여주기 위해 of메서드 작성
    //HospitalResponse 클래스 안에 생성자를 뚫어줘야 한다.
    //Static선언은 Hospital.of(hospital.get())으로 가독성이 좋다 Static이 아닐경우 -> hospital.get().of(hospital.get())
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getHospitalName(),
                hospital.getRoadNameAddress(), hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),hospital.getBusinessTypeName()
        ,   hospital.getTotalAreaSize());
    }

}
