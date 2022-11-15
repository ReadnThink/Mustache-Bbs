package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {

    //Service가 직접 Repository와 연결되어 Controller는 Repository를 사용하지 않고 Service만 사용하도록 한다.
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    public HospitalResponse getHospital(Integer id){
        //findById = Optional을 리턴한다.
        Optional<Hospital> optHospital = hospitalRepository.findById(id); //Entity -> Hospital의 of메소드를 이용해 Dto를 설정
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital); //Dto
        //Entity의 businessStatusCode가 13 = 영업중, 3 = 폐업 else 스트링 변환하여 setter로 Dto에 저장한다.
        if(hospital.getBusinessStatusCode() == 13){
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }
}