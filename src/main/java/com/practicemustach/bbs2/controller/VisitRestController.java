package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.VisitCreateRequest;
import com.practicemustach.bbs2.domain.dto.VisitResponse;
import com.practicemustach.bbs2.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/visits")
public class VisitRestController {
    private final VisitService visitService;

    public VisitRestController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<String> createVisit(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        return ResponseEntity.ok().body(visitService.create(visitCreateRequest, userName));
    }

    @GetMapping("/visit")
    public ResponseEntity<List<VisitResponse>> list(Pageable pageable) {
        return ResponseEntity.ok().body(visitService.findAllByPage(pageable));
    }
}
