package com.webtooni.webtooniverse.domain.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class MyListController {

    private final MyListService myListService;

    /**
     * 웹툰을 마이리스트에 추가한다.
     * @param myListRequestDto 웹툰 id와 마이리스트 유무 여부를 담은 Dto
     * @param userDetails 로그인 된 사용자 정보
     */
    @PostMapping("user/subscribe")
    public void createMyList(@RequestBody MyListRequestDto myListRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
        User user = userDetails.getUser();
        myListService.createMyList(user, myListRequestDto);
    }
}
