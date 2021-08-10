package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkPostService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkPostController {

    private final TalkPostService talkPostService;

    @PostMapping("talk")
    public TalkPost post(@RequestBody TalkPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return talkPostService.post(requestDto, user);
    }

    @GetMapping("talk")
    public List<TalkPostResponseDto> getPost() {
        return talkPostService.getPost();
    }

    @GetMapping("talk/{id}")
    public TalkPostResponseDto getPost(@PathVariable Long id) {
        return talkPostService.getOnePost(id);
    }

    @PutMapping("talk/{id}")
    public TalkResponseDto updatePost(@PathVariable Long id, @RequestBody TalkPostRequestDto requestDto) {
        return talkPostService.updatePost(id, requestDto);
    }

    @DeleteMapping("talk/{id}")
    public TalkResponseDto delete(@PathVariable Long id){
        return talkPostService.deletePost(id);
    }
}


/**
 * TODO service 쪽으로 돌릴 수 있는 거 돌리기
 */