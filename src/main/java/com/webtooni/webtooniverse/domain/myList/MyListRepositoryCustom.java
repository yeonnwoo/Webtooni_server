package com.webtooni.webtooniverse.domain.myList;

public interface MyListRepositoryCustom {
    boolean existsById(Long userId,Long webtoonId);
    MyList findMyList(Long webtoonId,Long userId);
}
