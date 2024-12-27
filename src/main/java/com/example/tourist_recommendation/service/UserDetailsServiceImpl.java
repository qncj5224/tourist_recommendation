package com.example.tourist_recommendation.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.example.tourist_recommendation.model.User;
import java.util.ArrayList;

/**
 * Spring Security의 UserDetailsService 구현체.
 * 사용자 인증을 위해 데이터베이스에서 사용자 정보를 로드한다.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * UserService 의존성을 주입받는 생성자.
     * @param userService 사용자 관련 비즈니스 로직을 처리하는 서비스
     */
    @Autowired
    public UserDetailsServiceImpl(@Lazy UserService userService) {  // @Lazy로 순환 참조 문제 방지
        this.userService = userService;
    }

    /**
     * 사용자 이름으로 사용자 정보를 로드합니다.
     *
     * @param username 사용자 이름
     * @return UserDetails Spring Security의 사용자 정보 객체
     * @throws UsernameNotFoundException 사용자가 존재하지 않을 경우 예외 발생
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);  // 사용자 검색
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),         // 사용자 이름
                user.getPassword(),         // 암호화된 비밀번호
                new ArrayList<>()           // 권한 목록 (현재 비어 있음)
        );
    }


    /**
     * 사용자 이름으로 사용자 정보를 검색합니다.
     *
     * @param username 사용자 이름
     * @return User 사용자 객체
     * @throws UsernameNotFoundException 사용자가 존재하지 않을 경우 예외 발생
     */
    private User findUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
    }

}
