package com.webtooni.webtooniverse.domain.user.domain;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> getBestReviewer();

    List<String> getUserGenre(Long userId);
}
