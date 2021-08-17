package com.webtooni.webtooniverse.domain.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MyListService {

  private final MyListRepository myListRepository;
  private final WebtoonRepository webtoonRepository;

  /**
   * 유저의 리스트에 추가하는 기능을 제공하는 구현체입니다.
   *
   * @param user             유저
   * @param myListRequestDto 웹툰의 id
   */
  public void createMyList(User user, MyListRequestDto myListRequestDto) {
    Long webtoonId = myListRequestDto.getToonId();
    boolean myListOrNot = myListRequestDto.isMyListOrNot();

    Webtoon webtoon = webtoonRepository.findById(webtoonId).orElseThrow(
        () -> new IllegalArgumentException("해당 웹툰은 존재하지 않습니다.")
    );
    if (myListOrNot) {
      MyList myList = MyList.of(user, webtoon);
      myListRepository.save(myList);
    } else {
      MyList myList = myListRepository.findMyList(webtoonId, user.getId());
      myListRepository.delete(myList);
    }
  }
}
