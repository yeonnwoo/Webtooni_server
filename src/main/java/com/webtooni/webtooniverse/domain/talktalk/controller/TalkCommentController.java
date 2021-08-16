package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkCommentService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkCommentController {

    private final TalkCommentService talkCommentService;

    @PostMapping("talk/{id}/comment")
    public TalkCommentPostingResponseDto postComment(@PathVariable Long id,
                                                     @RequestBody TalkCommentRequestDto requestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        checkUser(userDetails);
        User user = userDetails.getUser();
        return talkCommentService.commentPost(requestDto, user, id);
    }

    @GetMapping("talk/{id}/comment")
    public List<TalkCommentResponseDto> getComment(@PathVariable Long id) {
        return talkCommentService.getComment(id);
    }

    @PutMapping("talk/{id}/comment")
    public void updateComment(@RequestBody TalkCommentRequestDto requestDto,
                              @PathVariable Long id,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        checkUser(userDetails);
        talkCommentService.update(requestDto, id);
    }

    @DeleteMapping("talk/{id}/comment")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        checkUser(userDetails);
        talkCommentService.commentDelete(id);
    }

    private void checkUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유저 정보를 찾을 수 없습니다.");
        }
    }
}
