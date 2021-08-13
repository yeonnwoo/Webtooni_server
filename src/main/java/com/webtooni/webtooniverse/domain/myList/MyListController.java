package com.webtooni.webtooniverse.domain.myList;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class MyListController {

    private final MyListService myListService;

    @PostMapping("user/subscribe")
    public void createMyList(@RequestBody MyListRequestDto myListRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        //로그인된 유저 정보로 변경되어야함
        User user=userDetails.getUser();
        myListService.createMyList(user,myListRequestDto);
    }
}
