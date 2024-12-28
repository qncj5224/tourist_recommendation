package com.example.tourist_recommendation;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.repository.UserRepository;
import com.example.tourist_recommendation.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        // 테스트 실행 전에 모든 사용자 데이터 삭제
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterUser() throws Exception {
        // 테스트용 사용자 생성
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");

        // 사용자 등록
        userService.register(user);

        // 등록된 사용자 확인
        var savedUser = userRepository.findByUsername("testuser");
        assertTrue(savedUser.isPresent(), "사용자가 저장되지 않았음");

        // 비밀번호 암호화 확인
        assertTrue(passwordEncoder.matches("password", savedUser.get().getPassword()), "비밀번호가 올바르게 암호화되지 않았음");
    }

    @Test
    public void testUserPasswordEncryption() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");

        userService.saveUser(user);

        User savedUser = userRepository.findByUsername("testuser").orElse(null);

        assertThat(savedUser).isNotNull();
        assertTrue(passwordEncoder.matches("password123", savedUser.getPassword()), "비밀번호 암호화 확인 실패");
    }

    @Test
    public void testPasswordAndConfirmPasswordValidation() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setConfirmPassword("differentPassword"); // 비밀번호 불일치

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.register(user));
        assertTrue(exception.getMessage().contains("비밀번호와 비밀번호 확인이 일치하지 않습니다."));
    }


}
