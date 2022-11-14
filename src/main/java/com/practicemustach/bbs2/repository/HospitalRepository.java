package com.practicemustach.bbs2.repository;

import com.practicemustach.bbs2.domain.Hospital;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    //findby가 기본적으로 구현되어있다. 오버라이딩한것이다. //jpql을 활용해 필드를 메소드로 구현하는것
    //JPA의 기능이다.
    //Entity의 필드명을 정확히 입력해야한다.
    List<Hospital> findByBusinessTypeNameIn(List<String> BusinessTypes);
    List<Hospital> findByRoadNameAddressContaining(String keyword); //포함
    List<Hospital> findByRoadNameAddressStartsWith(String keyword); //시작
    List<Hospital> findByRoadNameAddressEndingWith(String keyword);//끝남
}
