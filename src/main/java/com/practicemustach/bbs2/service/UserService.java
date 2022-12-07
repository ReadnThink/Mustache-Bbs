package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.UserJoinRequest;
import com.practicemustach.bbs2.domain.entity.User;
import com.practicemustach.bbs2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String join(UserJoinRequest userJoinRequest) {

        //아이디 중복되는지
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent((user)-> {
                    throw new RuntimeException("이미 아이디가 존재합니다.");
                });
        User user = User.builder()
                .userName(userJoinRequest.getUserName())
                .password(userJoinRequest.getPassword())
                .build();
        userRepository.save(user);

        return "회원가입이 완료되었습니다";
    }
}
