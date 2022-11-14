package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.repository.HospitalRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    private final HospitalRepository hospitalRepository;


    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) {
//        Optional<Hospital> hospital = hospitalRepository.findById(id);
//        return ResponseEntity.ok().body(Hospital.of(hospital.get()));
//    }
}
