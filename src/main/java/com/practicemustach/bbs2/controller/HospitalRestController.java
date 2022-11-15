package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //controller 대신 RestController를 붙인다.
@RequestMapping("/api/v1/hospitals") //api기능을 한다고 암시를 주는것이다.
public class HospitalRestController {
    private final HospitalRepository hospitalRepository;


    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id); //Entity
        HospitalResponse hospitalResponse = Hospital.of(hospital.get()); //Dto
        return ResponseEntity.ok().body(hospitalResponse); //return은 Dto로 한다.
    }
}
