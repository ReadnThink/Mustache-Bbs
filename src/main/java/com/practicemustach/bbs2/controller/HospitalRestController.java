package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //controller 대신 RestController를 붙인다.
@RequestMapping("/api/v1/hospitals") //api기능을 한다고 암시를 주는것이다.
public class HospitalRestController {
    private final HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    //ResponseEntity = responseBody를 리턴한다. = Json형태로 변환하여 api에 뿌리겠다는 뜻이다.
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { //ResponseEntity도 Dto타입
        //Hospital.of를 Static으로 선언하여 바로 사용 할 수 있다.
        //Static선언은 Hospital.of(hospital.get())으로 가독성이 좋다 Static이 아닐경우 -> hospital.get().of(hospital.get())
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); //Dto
        return ResponseEntity.ok().body(hospitalResponse); //return은 Dto로 한다.
    }
}
