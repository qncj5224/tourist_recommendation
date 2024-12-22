package com.example.tourist_recommendation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterPageAccess() throws Exception {
        // 회원가입 페이지에 접근 가능 여부 확인
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginPageAccess() throws Exception {
        // 로그인 페이지에 접근 가능 여부 확인
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
}
