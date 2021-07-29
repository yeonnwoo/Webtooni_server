package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkLikeResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkLikeService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TalkLikeController {

    private final TalkLikeRepository talkLikeRepository;
    private final TalkLikeService talkLikeService;

    @PostMapping("/api/v1/talk/{id}/like")
    public TalkLikeResponseDto like(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        TalkLikeResponseDto responseDto = talkLikeService.postLike(id, userId);
        return responseDto;
    }
}
