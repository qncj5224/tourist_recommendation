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
        boolean isTaken = !checkUsernameAvailability(username);
        if (isTaken) {
            System.out.println("Username " + username + " is already taken");
        } else {
            System.out.println("Username " + username + " is available");
        }
        return isTaken;
    }

    /**
     * 아이디의 사용 가능 여부를 확인합니다.
     *
     * @param username 확인할 사용자 이름
     * @return 사용 가능하면 true, 사용 중이면 false
     */
    public boolean checkUsernameAvailability(String username) {
        return !userRepository.existsByUsername(username);
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

    /**
     * 사용자 등록.
     * 아이디 중복 여부를 확인하고, 비밀번호를 암호화하여 저장합니다.
     *
     * @param user 등록할 사용자 객체
     * @throws UsernameAlreadyExistsException 아이디가 이미 존재할 경우 예외 발생
     */
    public void register(User user) throws UsernameAlreadyExistsException {
        if (isUsernameTaken(user.getUsername())) {
            throw new UsernameAlreadyExistsException("이미 사용 중인 아이디입니다: " + user.getUsername());
        }
        user.setPassword(encryptPassword(user.getPassword()));
        saveUser(user);
    }

    /**
     * 비밀번호를 암호화합니다.
     *
     * @param password 원본 비밀번호
     * @return 암호화된 비밀번호
     */
    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 사용자 객체를 데이터베이스에 저장합니다.
     *
     * @param user 저장할 사용자 객체
     */
    private void saveUser(User user) {
        userRepository.save(user);
    }
}

