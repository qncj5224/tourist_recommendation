package com.example.tourist_recommendation.service;

import com.example.tourist_recommendation.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.example.tourist_recommendation.repository.UserRepository;

import java.util.ArrayList;

/**
 * Spring Security의 UserDetailsService 구현체.
 * 사용자 인증을 위해 데이터베이스에서 사용자 정보를 로드한다.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * UserRepository 의존성을 주입받는 생성자.
     * @param userRepository 사용자 정보를 관리하는 리포지토리
     */
    @Autowired
    public UserDetailsServiceImpl(@Lazy UserRepository userRepository) {  // @Lazy로 순환 참조 문제 방지
        this.userRepository = userRepository;
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
        // 데이터베이스에서 사용자 정보 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return user; // User 클래스 자체가 UserDetails를 구현하므로 바로 반환 가능
    }
}
