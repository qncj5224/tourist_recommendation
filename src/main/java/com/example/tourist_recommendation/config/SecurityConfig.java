package com.example.tourist_recommendation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 관련 설정을 정의하는 클래스.
 */
@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    /**
     * SecurityConfig 생성자.
     * @param userDetailsService 사용자 인증 정보를 제공하는 UserDetailsService
     * @Lazy 애너테이션으로 순환 참조 문제를 방지.
     */
    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * BCryptPasswordEncoder를 Bean으로 등록.
     * 비밀번호를 안전하게 암호화하기 위해 사용.
     * @return BCryptPasswordEncoder 인스턴스
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HTTP 요청 보안을 설정.
     * 특정 URL에 대한 접근 권한 및 로그인/로그아웃 설정을 정의.
     * @param http HttpSecurity 객체를 통해 보안 설정
     * @return SecurityFilterChain 인스턴스
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 인증이 필요 없는 URL 패턴 설정
                        .requestMatchers("/check-username", "/register", "/css/**", "/js/**").permitAll()
                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // 커스텀 로그인 페이지 설정
                        .loginPage("/login")
                        // 모든 사용자에게 로그인 페이지 접근 허용
                        .permitAll()
                )
                .logout(logout -> logout
                        // 로그아웃 요청은 인증 없이도 허용
                        .permitAll()
                );
        // 설정된 SecurityFilterChain 반환
        return http.build();
    }
}
