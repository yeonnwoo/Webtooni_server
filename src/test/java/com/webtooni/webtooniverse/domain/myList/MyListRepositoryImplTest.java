package com.webtooni.webtooniverse.domain.myList;

import static org.assertj.core.api.Assertions.assertThat;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class MyListRepositoryImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MyListRepository myListRepository;
    @Autowired
    WebtoonRepository webtoonRepository;

    @AfterEach
    void deleteAll() {
        myListRepository.deleteAll();
        userRepository.deleteAll();
        webtoonRepository.deleteAll();
    }

    @Nested
    @DisplayName("해당 웹툰이 유저의 마이리스트에 존재하는지 여부")
    class existsById {

        @Test
        @DisplayName("존재할 때")
        void exists() {
            //given
            User user = User.builder().userName("유저1").build();
            User savedUser = userRepository.save(user);

            Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url1").toonAuthor("작가1")
                .toonContent("내용").toonImg("이미지").toonPlatform("플랫폼").build();
            Webtoon savedWebtoon = webtoonRepository.save(webtoon);

            MyList myList = MyList.of(user, webtoon);
            myListRepository.save(myList);

            //when
            boolean exists = myListRepository.existsById(savedUser.getId(), savedWebtoon.getId());

            //then
            assertThat(exists).isTrue();
        }

        @Test
        @DisplayName("존재하지 않을 때")
        void notExists() {
            //given
            User user = User.builder().userName("유저1").build();
            User savedUser = userRepository.save(user);

            Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url1").toonAuthor("작가1")
                .toonContent("내용").toonImg("이미지").toonPlatform("플랫폼").build();
            Webtoon savedWebtoon = webtoonRepository.save(webtoon);

            MyList myList = MyList.of(user, webtoon);
            myListRepository.save(myList);

            //when
            boolean exists = myListRepository.existsById(savedUser.getId(), savedWebtoon.getId() + 1);

            //then
            assertThat(exists).isFalse();
        }

    }


    @Test
    @DisplayName("Mylist 객체 조회")
    void findMyList() {
        //given
        User user = User.builder().userName("유저1").build();
        User savedUser = userRepository.save(user);

        Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").realUrl("url1").toonAuthor("작가1")
            .toonContent("내용").toonImg("이미지").toonPlatform("플랫폼").build();
        Webtoon savedWebtoon = webtoonRepository.save(webtoon);

        MyList myList = MyList.of(user, webtoon);
        myListRepository.save(myList);

        //when
        MyList findMyList = myListRepository.findMyList(savedWebtoon.getId(), savedUser.getId());

        //then
        assertThat(myList.getUser().getUserName()).isEqualTo("유저1");
    }
}