package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByUserName(String userName);

    Optional<User> findBySocialId(String socialId);
}
