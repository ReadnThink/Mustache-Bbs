package com.practicemustach.bbs2.configuration;

import com.practicemustach.bbs2.domain.entity.User;
import com.practicemustach.bbs2.service.UserService;
import com.practicemustach.bbs2.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization을 잘못보냈습니다.");
            filterChain.doFilter(request,response);
            return;
        }

        //Token꺼내기
        String token = authorization.split(" ")[1];

        //Token 만료되었는지 확인
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request,response);
            return;
        }


        // UserName Token에서 꺼내기
        String userName = JwtUtil.getUserName(token,secretKey);
        log.info("userName:{}",userName);

        // User가져오기
        User user = userService.getUserByUserName(userName);
        log.info("Token Filter userName:{}", userName);

        //권한부여
        //Controller에서 Authentication으로 userName을 사용 할 수 있다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (userName, null, List.of(new SimpleGrantedAuthority("USER"
        )));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
