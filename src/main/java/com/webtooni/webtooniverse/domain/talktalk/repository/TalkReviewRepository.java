package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkReviewRepository extends JpaRepository<TalkReview, Long> {
    List<TalkReview> findAllById(Long id);
}
