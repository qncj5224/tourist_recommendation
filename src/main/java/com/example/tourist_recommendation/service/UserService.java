package com.example.tourist_recommendation.service;

import com.example.tourist_recommendation.model.User;
import com.example.tourist_recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 사용자 저장, 조회, 중복 체크, 회원가입 등 기능을 제공합니다.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * UserService 생성자.
     *
     * @param userRepository 사용자 데이터베이스 접근 객체
     * @param passwordEncoder 비밀번호 암호화를 위한 BCryptPasswordEncoder
     */
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 아이디가 이미 사용 중인지 확인합니다.
     *
     * @param username 확인할 사용자 이름
     * @return 아이디가 사용 중이면 true, 아니면 false
     */
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * 사용자 이름으로 사용자 정보를 조회합니다.
     *
     * @param username 찾을 사용자 이름
     * @return 사용자 객체를 Optional로 반환 (존재하지 않으면 빈 Optional 반환)
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(User user) {
        // 비밀번호와 비밀번호 확인 비교 (암호화 전)
        if (user.getPassword() == null || user.getConfirmPassword() == null) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인은 필수 항목입니다.");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }



    /**
     * 사용자 객체를 데이터베이스에 저장합니다.
     * 비밀번호가 이미 암호화된 상태에서만 호출해야 합니다.
     *
     * @param user 저장할 사용자 객체
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
