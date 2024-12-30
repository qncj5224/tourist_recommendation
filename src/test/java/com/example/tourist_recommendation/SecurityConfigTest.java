package com.example.tourist_recommendation;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {TouristRecommendationApplication.class})
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // 실제 빈 사용

    @BeforeEach
    public void setUp() {
        // 테스트 환경 초기화
        userService.findByUsername("testuser").ifPresent(user -> userService.deleteUser(user));

        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword"); // 원문 비밀번호
        testUser.setConfirmPassword("testpassword"); // 원문 비밀번호와 동일하게 설정
        testUser.setEmail("testuser@example.com");
        testUser.setPhoneNumber("1234567890");

        userService.register(testUser);
    }



    @Test
    public void testLoginSuccess() throws Exception {
        // /login 엔드포인트 테스트
        mockMvc.perform(post("/login")
                        .param("username", "testuser")
                        .param("password", "testpassword")
                        .with(csrf())) // CSRF 토큰 포함
                .andExpect(status().is3xxRedirection()); // 로그인 성공 시 리다이렉션 확인
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void printBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.stream(beanNames).forEach(System.out::println);
    }
}


