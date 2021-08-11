package com.webtooni.webtooniverse.domain.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByUserName(String userName);
    Optional<User> findByKakaoId(Long kakaoId);
    Optional<User> findByNaverId(Long naverId);
}
