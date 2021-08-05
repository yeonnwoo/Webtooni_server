package com.webtoon.demo.domain.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGenreRepository extends JpaRepository<UserGenre, Long> {
}