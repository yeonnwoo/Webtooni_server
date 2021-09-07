package com.webtooni.webtooniverse.domain.myList;

import static org.assertj.core.api.Assertions.assertThat;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyListRepositoryImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MyListRepository myListRepository;
    @Autowired
    WebtoonRepository webtoonRepository;

    @BeforeEach
    void dummyData() {
        User user = User.builder().userName("유저1").build();
        userRepository.save(user);

        Webtoon webtoon = Webtoon.builder().toonTitle("웹툰1").build();
        webtoonRepository.save(webtoon);

        MyList myList = MyList.of(user, webtoon);
        myListRepository.save(myList);
    }

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
            Long userId = userRepository.findByUserName("유저1").get().getId();
            Long webtoonId = webtoonRepository.findAll().get(0).getId();

            //when
            boolean exists = myListRepository.existsById(userId, webtoonId);

            //then
            assertThat(exists).isTrue();
        }

        @Test
        @DisplayName("존재하지 않을 때")
        void notExists() {
            //given
            Long userId = userRepository.findByUserName("유저1").get().getId();
            Long webtoonId = webtoonRepository.findAll().get(0).getId();

            //when
            boolean exists = myListRepository.existsById(userId, webtoonId + 1);

            //then
            assertThat(exists).isFalse();
        }

    }


    @Test
    @DisplayName("Mylist 객체 조회")
    void findMyList() {
        //given
        Long userId = userRepository.findByUserName("유저1").get().getId();
        Long webtoonId = webtoonRepository.findAll().get(0).getId();

        //when
        MyList myList = myListRepository.findMyList(webtoonId, userId);

        //then
        assertThat(myList).isNotNull();
    }
}