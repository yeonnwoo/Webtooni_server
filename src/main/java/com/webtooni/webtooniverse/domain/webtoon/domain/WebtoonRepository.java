package com.webtooni.webtooniverse.domain.webtoon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
    List<Webtoon> findByReviewCountLessThanEqual(int number);
//    List<Webtoon> findByReviewCountBetween(3, 1);
}
