package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.VisitCreateRequest;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.domain.entity.User;
import com.practicemustach.bbs2.domain.entity.Visit;
import com.practicemustach.bbs2.repository.HospitalRepository;
import com.practicemustach.bbs2.repository.UserRepository;
import com.practicemustach.bbs2.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    public VisitService(VisitRepository visitRepository, UserRepository userRepository, HospitalRepository hospitalRepository) {
        this.visitRepository = visitRepository;
        this.userRepository = userRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public String create(VisitCreateRequest visitCreateRequest, String userName) {
        Hospital hospital = hospitalRepository.findById(visitCreateRequest.getHospitalId())
                .orElseThrow(() -> new RuntimeException("해당 hospital id가 없습니다."));
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new RuntimeException("일치하는 userName이 없습니다."));

        Visit visit = Visit.builder()
                .disease(visitCreateRequest.getDisease())
                .amount(visitCreateRequest.getAmount())
                .user(user)
                .hospital(hospital)
                .build();
        return "방문 리뷰 등록이 완료되었습니다.";
    }
}
