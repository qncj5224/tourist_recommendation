package com.example.tourist_recommendation.model;

import jakarta.persistence.*;

/**
 * User 엔티티는 사용자 정보를 저장하는 클래스이다.
 * 데이터베이스 테이블 `users`와 매핑되며, 사용자 아이디, 비밀번호, 이메일, 전화번호 등의 정보를 포함한다.
 */
@Entity
@Table(name = "users") // 데이터베이스의 "users" 테이블과 매핑
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key 자동 생성 전략
    private Long id; // 사용자 고유 식별자 (자동 증가)

    @Column(nullable = false, unique = true) // null 불가 및 유일성 제약 조건
    private String username; // 사용자 아이디

    @Column(nullable = false) // null 불가 제약 조건
    private String password; // 사용자 비밀번호 (암호화된 상태로 저장)

    @Column(nullable = false) // null 불가 제약 조건
    private String email; // 사용자 이메일

    @Column(nullable = false) // null 불가 제약 조건
    private String phoneNumber; // 사용자 전화번호

    @Transient // 이 필드는 데이터베이스에 저장되지 않음
    private String confirmPassword; // 비밀번호 확인용 필드 (UI 상에서 사용)

    // Getters and Setters
    /**
     * @return 사용자 고유 식별자
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 사용자 고유 식별자를 설정
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 사용자 아이디
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username 사용자 아이디를 설정
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return 사용자 비밀번호
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 사용자 비밀번호를 설정
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 사용자 이메일
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 사용자 이메일을 설정
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 사용자 전화번호
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber 사용자 전화번호를 설정
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return 비밀번호 확인 필드
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword 비밀번호 확인 필드를 설정
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
