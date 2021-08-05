package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.dto.TalkLikeResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TalkLikeController {

    private final TalkLikeService talkLikeService;

    @PostMapping("/api/v1/talk/{id}/like")
    public TalkLikeResponseDto like(@PathVariable Long id){
        TalkLikeResponseDto responseDto = talkLikeService.postLike(id);
        return responseDto;
    }
}
