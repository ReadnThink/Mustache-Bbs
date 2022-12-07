package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.domain.dto.UserJoinRequest;
import com.practicemustach.bbs2.service.HospitalService;
import com.practicemustach.bbs2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //controller 대신 RestController를 붙인다.
@RequestMapping("/api/v1/hospitals") //api기능을 한다고 암시를 주는것이다.
public class HospitalRestController {
    private final HospitalService hospitalService;

    private final UserService userService;
    public HospitalRestController(HospitalService hospitalService, UserService userService) {
        this.hospitalService = hospitalService;
        this.userService = userService;
    }

    //ResponseEntity = responseBody를 리턴한다. = Json형태로 변환하여 api에 뿌리겠다는 뜻이다.
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { //ResponseEntity도 Dto타입
        //Hospital.of를 Static으로 선언하여 바로 사용 할 수 있다.
        //Static선언은 Hospital.of(hospital.get())으로 가독성이 좋다 Static이 아닐경우 -> hospital.get().of(hospital.get())
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); //Dto
        return ResponseEntity.ok().body(hospitalResponse); //return은 Dto로 한다.
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest userJoinRequest) {
        return ResponseEntity.ok().body(userService.join(userJoinRequest));
    }
}
