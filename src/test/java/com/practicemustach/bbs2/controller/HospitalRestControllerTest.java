package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.dto.HospitalResponse;
import com.practicemustach.bbs2.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    //mockMvc = 서블릿 컨테이너의 구동 없이 가상의 MVC환경에서 모의 HTTP 서블릿을 요청하는 유틸리티이다.
    @Autowired
    MockMvc mockMvc; //컨트롤러의 API를 테스트하기위해 객체생성

    @MockBean //Autowired아님 --> HospitalService는 테스트를 위해 가짜 객체를 쓰겠다는 뜻이다.
    HospitalService hospitalService; // 가짜 객체를 쓰면 좋은점 DB와 상관없이 테스트 가능

    @Test
    @DisplayName("1개의 Json 형태로 Response가 잘 오는지")//비지니스로직(Service를 검증하지 않음) Controller만 검증한다.
    void jsonResponse() throws Exception {
        //HospitalResponse에 @builder를 선언해주어 생성자의 단점인 순서대로 넣는것을 보완할 수 있다.
        //그냥 넣고싶은 타입에 값을 넣으면 된다.

        HospitalResponse hospitalResponse = HospitalResponse.builder().
                id(2321).
                roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)").
                hospitalName("노소아청소년과의원").
                patientRoomCount(0).
                totalNumberOfBeds(0).
                businessTypeName("의원").
                totalAreaSize(0.0f).
                businessStatusName("영업중").
                build();

        //어떤 메서드가 호출되고, 어떤 파라미터를 주입받는지, 가정한 후 willReturn()메서드를 통해 어떤 결과를 리턴할 것인지 정의
        given(hospitalService.getHospital(2321))
                .willReturn(hospitalResponse);

        int hospitalId = 2321;

        //앞에서 Autowired한 mockMvc
        String url = String.format("/api/v1/hospitals/%d", hospitalId);

        // perform = 서버로 URL요청을 보내는 것처럼 통신테스트 코드를 작성해서 컨트롤러를 테스트 할 수 있다.
        // perform의 리턴값으로 ResultActions 객체가 리턴되는데, andExpect()를 통해 결과값 검증을 할 수 있다.
        // andExpect()메서드에는 ResultMatcher()를 활용하는데, MockMvcResultMatchers클래스에 정의돼 있는 메서드를 활용해 생성 할 수 있습니다.
        mockMvc.perform(get(url))
                .andExpect(status().isOk()) // isOk = 응답상태 코드가 200인지 확인
                //jsonPath exists()해당 Path에 값이 존재하는지 확인
                //jsonPath value 해당 Path에 값이 "expectedValue"와 일치하는지 확인
                .andExpect(jsonPath("$.hospitalName").exists()) // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                //요청과 응답의 전체 내용확인
                .andDo(print()); // http request, response내역을 출력 해라

        //지정된 메서드가 실행됐는지 검증 - given에 정의된 동작과 대응한다.
        verify(hospitalService).getHospital(hospitalId);// getHospital()메소드의 호출이 있었는지 확인
    }
}