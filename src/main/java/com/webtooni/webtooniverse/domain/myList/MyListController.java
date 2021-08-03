package com.webtooni.webtooniverse.domain.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyListController {

    private final MyListService myListService;

    @PostMapping("/api/v1/user/subscribe")
    public void createMyList(@RequestBody Long webtoonId)
    {
        /**
         * 로그인된 유저 정보로 변경 되어야함
         */
        User user=User.builder()
                .userImg(1)
                .build();

        myListService.createMyList(user,webtoonId);
    }
}
