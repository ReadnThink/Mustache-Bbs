package com.practicemustach.bbs2.service;

import com.practicemustach.bbs2.domain.dto.UserJoinRequest;
import com.practicemustach.bbs2.domain.dto.UserLoginRequest;
import com.practicemustach.bbs2.domain.entity.User;
import com.practicemustach.bbs2.repository.UserRepository;
import com.practicemustach.bbs2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public String join(UserJoinRequest userJoinRequest) {

        //아이디 중복되는지
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent((user)-> {
                    throw new RuntimeException("이미 아이디가 존재합니다.");
                });
        User user = User.builder()
                .userName(userJoinRequest.getUserName())
                .password(encoder.encode(userJoinRequest.getPassword()))
                .build();
        userRepository.save(user);

        return "회원가입이 완료되었습니다";
    }

    public String login(UserLoginRequest userLoginRequest) {
        //아이디가 있는지
        User user = userRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(()-> new RuntimeException("userName이 일치하지 않습니다."));

        //패스워드가 맞는지
        if (!encoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        String token = JwtUtil.createToken(user.getUserName(), secretKey, expiredMs);
        //토큰발행
        return token;
    }

    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("해당 Id가 없습니다"));
        return user;
    }
}
