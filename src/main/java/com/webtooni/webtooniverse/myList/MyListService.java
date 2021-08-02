package com.webtooni.webtooniverse.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Transactional
@Service
public class MyListService {

    private final MyListRepository myListRepository;

    private final WebtoonRepository webtoonRepository;

    public void createMyList(User user, Long webtoonId)
    {
        Webtoon webtoon = webtoonRepository.findById(webtoonId).orElseThrow(
                () -> new IllegalArgumentException("해당 웹툰은 존재하지 않습니다.")
        );

        MyList myList = new MyList();
        myList.createMyList(user,webtoon);

        myListRepository.save(myList);

    }
}
