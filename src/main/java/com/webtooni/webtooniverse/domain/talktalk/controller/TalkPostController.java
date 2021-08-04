package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkPostService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class TalkPostController {

    private final TalkPostService talkPostService;
    private final TalkPostRepository talkPostRepository;

    @PostMapping("/api/v1/talk")
    public TalkPost post(@RequestBody TalkPostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        TalkPost talkPost = talkPostService.post(requestDto, user);
        return talkPost;
    }

    @GetMapping("/api/v1/talk")
    public List<TalkPost> getPost() {
        return talkPostRepository.findAll();
    }

    @GetMapping("/api/v1/talk/{id}")
    public TalkPost getPost(@PathVariable Long id) {
        TalkPost talkPost =  talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 포스팅이 존재하지 않습니다.")
        );
        return talkPost;
    }

    @PutMapping("/api/v1/talk/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody TalkPostRequestDto requestDto) {
        talkPostService.updatePost(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/v1/talk/{id}")
    public void delete(@PathVariable Long id){
        talkPostRepository.deleteById(id);
    }
}
