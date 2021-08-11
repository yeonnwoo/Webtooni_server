package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkCommentService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@RestController
public class TalkCommentController {

    private final TalkCommentService talkCommentService;

    @PostMapping("talk/{id}/comment")
    public TalkBoardComment postComment(@PathVariable Long id, @RequestBody TalkCommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return talkCommentService.commentPost(requestDto, user, id);
    }

    @GetMapping("talk/{id}/comment")
    public List<TalkCommentResponseDto> getComment(@PathVariable Long id) {
        return talkCommentService.getComment(id);
    }

    @PutMapping("talk/{id}/comment")
    public TalkResponseDto updateComment(@RequestBody TalkCommentRequestDto requestDto, @PathVariable Long id){
        return talkCommentService.update(requestDto, id);
    }

    /**
     * TODO service 쪽으로 돌릴 수 있는 거 돌리기
     */

    @DeleteMapping("talk/{id}/comment")
    public TalkResponseDto delete(@PathVariable Long id){
        return talkCommentService.commentDelete(id);
    }
}
