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

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    private boolean isInvalidUsername(String username) {
        return username == null || username.isEmpty();
    }

    @PostMapping("/check-username")
    @ResponseBody
    public String checkUsername(@RequestParam("username") String username) {
        if (isInvalidUsername(username)) {
            return "아이디를 입력해주세요.";
        }
        // 변경된 메서드 사용
        return userService.isUsernameTaken(username) ? "이미 존재하는 아이디입니다." : "아이디 사용 가능";
    }

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

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
        try {
            userService.register(user);
            model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
            return "login";
        } catch (Exception e) {
            result.rejectValue("username", "error.user", e.getMessage());
            return "register";
        }
    }
}
