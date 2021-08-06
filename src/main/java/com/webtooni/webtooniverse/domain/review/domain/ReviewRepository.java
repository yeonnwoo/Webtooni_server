package com.webtooni.webtooniverse.domain.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByCreatedAt();
    List<Review> findAllByOrderByLikeCountDesc();
}
