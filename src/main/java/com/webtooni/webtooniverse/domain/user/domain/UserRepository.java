package com.webtooni.webtooniverse.domain.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUserName(String userName);

    Optional<User> findBySocialId(String socialId);
}
