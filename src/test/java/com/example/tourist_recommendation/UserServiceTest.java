package com.example.tourist_recommendation;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.repository.UserRepository;
import com.example.tourist_recommendation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() throws Exception {
        // 테스트용 사용자 생성
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");

        // 사용자 등록
        userService.register(user);

        // 등록된 사용자 확인
        var savedUser = userRepository.findByUsername("testuser");
        assertTrue(savedUser.isPresent(), "사용자가 저장되지 않았음");

        // 비밀번호 암호화 확인
        assertTrue(new BCryptPasswordEncoder().matches("password", savedUser.get().getPassword()), "비밀번호가 올바르게 암호화되지 않았음");
    }

}
