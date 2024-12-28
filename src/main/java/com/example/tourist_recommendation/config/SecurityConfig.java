package com.example.tourist_recommendation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 관련 설정을 정의하는 클래스입니다.
 * - 사용자 인증 및 권한 부여 설정
 * - HTTP 요청 보안 설정
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
     * BCryptPasswordEncoder를 Bean으로 등록합니다.
     * 비밀번호를 안전하게 암호화하기 위해 사용됩니다.
     * @return BCryptPasswordEncoder 인스턴스
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HTTP 요청 보안을 설정합니다.
     * - 인증이 필요 없는 URL 패턴 정의
     * - 커스텀 로그인 및 로그아웃 설정
     * - CSRF 및 HTTPS 설정
     *
     * @param http HttpSecurity 객체를 통해 보안 설정
     * @return SecurityFilterChain 인스턴스
     * @throws Exception 설정 중 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
                    // 인증이 필요 없는 URL
                    authorize
                            .requestMatchers("/check-username", "/register", "/css/**", "/js/**").permitAll()
                            // 그 외 모든 요청은 인증 필요
                            .anyRequest().authenticated();
                })
                .formLogin(form -> {
                    // 커스텀 로그인 페이지 설정
                    form
                            .loginPage("/login")
                            .permitAll();
                })
                .logout(logout -> {
                    // 로그아웃 요청 허용
                    logout.permitAll();
                })
                .csrf(csrf -> csrf
                        // 특정 URL에서 CSRF 보호 비활성화
                        .ignoringRequestMatchers("/api/**")
                )
                .requiresChannel(channel -> channel
                        // HTTPS 사용 강제
                        .anyRequest().requiresInsecure() // HTTPS 강제 비활성화 (로컬)
                );
        return http.build();
    }
}
