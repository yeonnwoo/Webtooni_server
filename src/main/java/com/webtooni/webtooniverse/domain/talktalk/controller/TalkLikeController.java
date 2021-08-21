package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkLikeService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkLikeController {

    private final TalkLikeService talkLikeService;
    private final UserRepository userRepository;


    /**
     * 게시글에 좋아요를 누른다.
     *
     * @param id postId
     * @return TalkResponseDto
     */
    @PostMapping("talk/{id}/like")
    public TalkResponseDto like(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
//        }
//        User user = userDetails.getUser();
        User user = userDetails.getUser();
        return talkLikeService.postLike(id,user);
    }

}
