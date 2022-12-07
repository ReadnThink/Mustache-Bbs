package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.VisitCreateRequest;
import com.practicemustach.bbs2.domain.dto.VisitResponse;
import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.domain.entity.User;
import com.practicemustach.bbs2.domain.entity.Visit;
import com.practicemustach.bbs2.repository.HospitalRepository;
import com.practicemustach.bbs2.repository.UserRepository;
import com.practicemustach.bbs2.repository.VisitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        // 병원 정보를 읽어오는 로직
        Hospital hospital = hospitalRepository.findById(visitCreateRequest.getHospitalId())
                .orElseThrow(() -> new RuntimeException("해당 hospital id가 없습니다."));
        // Token에서 받은 UserName을 통해 User를 받아오는 로직
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new RuntimeException("일치하는 userName이 없습니다."));

        // 앞서 얻은 hospital정보와 user를 통해 방문리뷰 저장하는 로직
        Visit visit = Visit.builder()
                .disease(visitCreateRequest.getDisease())
                .amount(visitCreateRequest.getAmount())
                .user(user)
                .hospital(hospital)
                .build();
        visitRepository.save(visit);
        return "방문 리뷰 등록이 완료되었습니다.";
    }

    public List<VisitResponse> findAllByPage(Pageable pageable) {
        Page<Visit> visits = visitRepository.findAll(pageable);

        return visits.stream()
                .map(Visit::toResponse)
                .collect(Collectors.toList());
    }
}
