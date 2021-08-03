package com.webtooni.webtooniverse.domain.join;

import com.webtooni.webtooniverse.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGenreRepository extends JpaRepository<UserGenre, Long> {
}
