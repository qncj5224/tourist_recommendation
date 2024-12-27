package com.example.tourist_recommendation.model;

import jakarta.persistence.*;

/**
 * User 엔티티는 사용자 정보를 저장하는 클래스입니다.
 * 데이터베이스 테이블 `users`와 매핑되며, 사용자 아이디, 비밀번호, 이메일, 전화번호 등의 정보를 포함합니다.
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

    // Getter 및 Setter (유효성 검사 추가)
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디는 필수 항목입니다.");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수 항목입니다.");
        }
        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수 항목입니다.");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("전화번호는 필수 항목입니다.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
