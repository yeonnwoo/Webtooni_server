package com.webtooni.webtooniverse.domain.webtoon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

}
