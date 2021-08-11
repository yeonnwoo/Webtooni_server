package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkLikeResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkLikeController {

    private final TalkLikeService talkLikeService;

    @PostMapping("talk/{id}/like")
    public TalkLikeResponseDto like(@PathVariable Long id){
        return talkLikeService.postLike(id);
    }
}
