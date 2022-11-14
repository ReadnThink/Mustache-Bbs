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


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 15,sort = "id") Pageable pageable){
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size:{}",hospitals.getSize());
        model.addAttribute("hospitals",hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("first", pageable.first().getPageNumber()); //first page값
        model.addAttribute("last",111919 / 15); //last page값
        System.out.println("first page = "+pageable.first() + "    next page = " + pageable.next().getPageNumber());
        return "hospital/list";
    }
}
