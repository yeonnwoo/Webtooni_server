package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.AllTalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkPostService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkPostController {

    private final TalkPostService talkPostService;


    //게시글 쓰기
    @PostMapping("talk")
    public TalkPostPostingResponseDto post(@RequestBody TalkPostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();

        return talkPostService.post(requestDto, user);
    }

    //게시글 상세페이지 불러오기
    @GetMapping("talk/{id}")
    public TalkPostResponseDto getOnePost(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return talkPostService.getOnePost(id, userDetails);
    }

    //게시글 수정하기
    @PutMapping("talk/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody TalkPostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);

        talkPostService.updatePost(id, requestDto);
    }

    //게시글 삭제하기
    @DeleteMapping("talk/{id}")
    public void deletePost(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        checkUser(userDetails);
        User user = userDetails.getUser();

        talkPostService.deletePost(id, user);
    }


    //모든 톡톡 게시글 불러오기
    @GetMapping("talk")
    public AllTalkPostPageResponseDto getPost(
        @PathParam("page") int page,
        @PathParam("size") int size
    ) {
        return talkPostService.getPost(page, size);
    }

    private void checkUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
    }

}
