package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("3일때 폐업 13일때 오픈")
    void findByIdWithStatusName(){
        Hospital hospital1 = Hospital.builder()
                        .id(1)
                        .businessStatusCode(3).build();
        Hospital hospital2 = Hospital.builder()
                .id(2)
                .businessStatusCode(13).build();

        Mockito.when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital1));
        HospitalResponse closedHospitalResponse = hospitalService.getHospital(1);
        assertEquals("폐업",closedHospitalResponse.getBusinessStatusName());

        Mockito.when(hospitalRepository.findById(2)).thenReturn(Optional.of(hospital2));
        HospitalResponse openHospitalResponse = hospitalService.getHospital(2);
        assertEquals("영업중",openHospitalResponse.getBusinessStatusName());

    }
}