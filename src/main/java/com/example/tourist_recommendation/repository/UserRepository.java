package com.example.tourist_recommendation.repository;

import com.example.tourist_recommendation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * UserRepository는 사용자 데이터에 접근하기 위한 JPA 리포지토리이다.
 * Spring Data JPA를 사용하여 데이터베이스와의 상호작용을 간소화한다.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 사용자 이름으로 사용자 정보를 조회한다.
     * @param username 조회하려는 사용자 이름
     * @return 해당 사용자 이름을 가진 User 객체를 포함하는 Optional 객체
     */
    Optional<User> findByUsername(String username);

    /**
     * 특정 사용자 이름이 이미 존재하는지 확인한다.
     * @param username 확인하려는 사용자 이름
     * @return 사용자 이름이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByUsername(String username);
}
