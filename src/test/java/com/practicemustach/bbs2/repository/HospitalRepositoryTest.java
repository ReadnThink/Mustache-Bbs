package com.practicemustach.bbs2.repository;

import com.practicemustach.bbs2.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void findByBusinessNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        printHospitalNameAndAddress(hospitals);
    }

    @Test // like="%송파구%" containing = %안넣어도 된다.
    void containing(){
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void startsWith(){
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressStartsWith("경희");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void endsWith(){
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressEndingWith("병원");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void between(){
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetween(10, 20);
        //정렬하여 출력
        List<Hospital> orderByHospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(10, 20);
//        printHospitalNameAndAddress(hospitals);
        printHospitalNameAndAddress(orderByHospitals);
    }

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
        }

        System.out.println(hospitals.size());
    }
}