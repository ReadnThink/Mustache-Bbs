package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.VisitCreateRequest;
import com.practicemustach.bbs2.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/visits")
public class visitRestController {
    private final VisitService visitService;

    public visitRestController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("")
    public ResponseEntity<String> createVisit(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        return ResponseEntity.ok().body(visitService.create(visitCreateRequest, userName));
    }
}
