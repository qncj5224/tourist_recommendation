package com.example.tourist_recommendation.controller;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

/**
 * UserController는 사용자와 관련된 요청을 처리하는 역할을 한다.
 * 회원가입, 로그인, 아이디 중복 확인 등의 기능을 제공한다.
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
     * 회원가입 폼 페이지를 반환.
     * @param model 사용자 객체를 뷰로 전달하기 위한 모델
     * @return register.html 페이지
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // 새 User 객체를 모델에 추가
        return "register"; // 회원가입 페이지로 이동
    }

    /**
     * 아이디 중복 확인 요청 처리.
     * @param username 사용자가 입력한 아이디
     * @return 아이디 사용 가능 여부 메시지
     */
    @PostMapping("/check-username")
    @ResponseBody
    public String checkUsername(@RequestParam("username") String username) {
        System.out.println("요청된 아이디: " + username); // 로그 출력
        if (username == null || username.isEmpty()) {
            return "아이디를 입력해주세요.";
        }

        boolean isAvailable = userService.checkUsernameAvailability(username); // 중복 여부 확인
        System.out.println("사용 가능 여부: " + isAvailable); // 로그 출력
        return isAvailable ? "아이디 사용 가능" : "이미 존재하는 아이디입니다.";
    }

    /**
     * 회원가입 요청 처리.
     * @param user 사용자 입력 데이터
     * @param model 오류 메시지를 전달하기 위한 모델
     * @return 회원가입 성공 시 로그인 페이지로 리다이렉트, 실패 시 다시 회원가입 페이지로 이동
     */
    @PostMapping("/register")
    public String register(User user, Model model) {
        // 아이디가 입력되지 않은 경우
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            model.addAttribute("error", "아이디를 입력해주세요.");
            return "register";
        }

        // 아이디 중복 확인
        if (!userService.checkUsernameAvailability(user.getUsername())) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "register";
        }

        // 회원가입 처리
        try {
            userService.register(user); // 서비스에서 회원가입 처리
            return "redirect:/login"; // 성공 시 로그인 페이지로 이동
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다.");
            return "register"; // 실패 시 회원가입 페이지로 이동
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

    /**
     * 사용자 등록 처리 (단순 성공/실패 처리).
     * @param user 사용자 입력 데이터
     * @param result 입력 데이터 검증 결과
     * @return 성공 시 성공 페이지로 리다이렉트
     */
    @PostMapping("/process-registratio")
    public String processRegistration(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register"; // 검증 실패 시 회원가입 페이지로 이동
        }
        return "redirect:/success"; // 성공 시 성공 페이지로 리다이렉트
    }
}
