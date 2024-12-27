package com.example.tourist_recommendation.service;
/**
 * 아이디 중복 예외 클래스.
 * 아이디가 이미 사용 중일 때 발생합니다.
 */
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}