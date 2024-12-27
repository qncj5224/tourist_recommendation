package com.example.tourist_recommendation.controller;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

/**
 * UserController는 사용자와 관련된 요청을 처리합니다.
 * 주요 기능:
        * - 회원가입
 * - 로그인
 * - 아이디 중복 확인
 */
@Controller
public class UserController {

    private final UserService userService;

    /**
     * UserController 생성자.
     * @param userService 사용자와 관련된 비즈니스 로직을 처리하는 서비스
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입 폼 페이지를 반환합니다.
     * @param model 사용자 객체를 뷰로 전달하기 위한 모델
     * @return 회원가입 페이지 (register.html)
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // 새 User 객체를 모델에 추가
        return "register"; // 회원가입 페이지로 이동
    }

    /**
     * 아이디 유효성을 검사하는 유틸리티 메서드.
     * @param username 입력된 사용자 아이디
     * @return 아이디가 null 또는 빈 문자열이면 true, 그렇지 않으면 false
     */
    private boolean isInvalidUsername(String username) {
        return username == null || username.isEmpty();
    }

    /**
     * 아이디 중복 확인 요청을 처리합니다.
     * @param username 입력된 사용자 아이디
     * @return 아이디 사용 가능 여부 메시지
     */
    @PostMapping("/check-username")
    @ResponseBody
    public String checkUsername(@RequestParam("username") String username) {
        if (isInvalidUsername(username)) {
            return "아이디를 입력해주세요.";
        }
        return userService.checkUsernameAvailability(username) ? "아이디 사용 가능" : "이미 존재하는 아이디입니다.";
    }

    /**
     * 회원가입 요청을 처리합니다.
     * 사용자 입력값의 유효성을 검사하고, 회원가입 성공 여부를 처리합니다.
     *
     * @param user   사용자 입력 데이터
     * @param result 유효성 검사 결과
     * @param model  뷰로 전달할 모델
     * @return 성공 시 로그인 페이지로 리다이렉트, 실패 시 회원가입 페이지로 이동
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "유효하지 않은 입력입니다.");
            return "register";
        }

        try {
            userService.register(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 예상치 못한 오류가 발생했습니다.");
            return "register";
        }
    }

    /**
     * 로그인 폼 페이지를 반환.
     * @return login.html 페이지
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 로그인 페이지로 이동
    }

    /**
     * 사용자 등록 처리 (추가적인 데이터 바인딩 지원).
     * @param user 사용자 입력 데이터
     * @param result 입력 데이터 검증 결과
     * @param model 성공/실패 메시지를 전달하기 위한 모델
     * @return 성공 시 로그인 페이지로 이동, 실패 시 다시 회원가입 페이지로 이동
     */
    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        try {
            userService.register(user); // 서비스에서 회원가입 처리
            model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
            return "login"; // 성공 시 로그인 페이지로 이동
        } catch (Exception e) {
            result.rejectValue("username", "error.user", e.getMessage());
            return "register"; // 실패 시 다시 회원가입 페이지로 이동
        }
    }
}
