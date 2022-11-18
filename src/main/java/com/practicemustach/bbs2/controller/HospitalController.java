package com.practicemustach.bbs2.controller;

import com.practicemustach.bbs2.domain.entity.Hospital;
import com.practicemustach.bbs2.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model, @PageableDefault(size = 15,sort = "id") Pageable pageable){
        // keyword는 어떻게 받을 것인가.
        // mustache form에서 get을하면 keyword=00 형태로 받아온다.
        // @RequestParam을 통해 키 = 값형태로 받아올 수 있다.
        // keyword추가 후 keyword가 없을경우 Repository에서 find를 하지 못한다. -> @RequestParam(required=false)로 해결 -> 필수조건 아니라는뜻
        // keyword가 없어도 페이지는 나오지만, keyword검색 후 다음 페이지로 이동시 keyword의 값과 일치한 정보가 나오지 않는다..
/*
         list.mustache에서 keyword값을 받게 한다.
         <a class="page-link" href="?page={{previous}}&keyword={{keyword}}">Previous </a>
         <a class="page-link" href="?page={{next}}&keyword={{keyword}}">Next </a>

         해결 안되어 list.mustache에 keyword 있을때 없을때로 2case로 만들어 구현
*/
        log.info("keyword:{}",keyword);
        Page<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        log.info("size:{}",hospitals.getSize());
        model.addAttribute("hospitals",hospitals);
        model.addAttribute("keyword",keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
//        model.addAttribute("first", pageable.first().getPageNumber()); //first page값
//        model.addAttribute("last",111919 / 15); //last page값
//        System.out.println("first page = "+pageable.first() + "    next page = " + pageable.next().getPageNumber());
        return "hospital/list";
    }
}
