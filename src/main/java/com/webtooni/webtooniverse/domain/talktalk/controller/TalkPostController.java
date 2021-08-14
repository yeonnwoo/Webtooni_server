package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.*;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkPostService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkPostController {

    private final TalkPostService talkPostService;

    @PostMapping("talk")
    public TalkPostPostingResponseDto post(@RequestBody TalkPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다."); }
        User user = userDetails.getUser();
        return talkPostService.post(requestDto, user);
    }

//    @GetMapping("talk")
//    public TalkPostPageableResponseDto getPost(@PathParam("page") int page, @PathParam("size") int size) {
//        return talkPostService.getPost(page, size);
//    }

    @GetMapping("talk/{id}")
    public TalkPostResponseDto getPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return talkPostService.getOnePost(id, userDetails);
    }

    @PutMapping("talk/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody TalkPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다."); }
        talkPostService.updatePost(id, requestDto);
    }

    @DeleteMapping("talk/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다."); }
        talkPostService.deletePost(id);
    }

    //모든 톡톡 게시글 불러오기
    @GetMapping("talk")
    public AllTalkPostPageResponseDto getPost(
            @PathParam("page") int page,
            @PathParam("size") int size
    ){
        return talkPostService.getPost(page, size);
    }


}


/**
 * TODO service 쪽으로 돌릴 수 있는 거 돌리기
 */