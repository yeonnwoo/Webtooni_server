package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkReviewGetRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkReviewRepository;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TalkReviewController {

    private final TalkReviewService talkReviewService;

    @PostMapping("talk/{id}comment")
    public TalkBoardComment postComment(@PathVariable Long id, @RequestBody TalkReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return talkReviewService.commentPost(requestDto, user, id);
    }

    @GetMapping("talk/{id}/comment")
    public List<TalkReviewGetRequestDto> getComment(@PathVariable Long id) {
        return talkReviewService.getComment(id);
    }

    @PutMapping("talk/{id}/comment")
    public TalkBoardComment updateComment(@RequestBody TalkReviewRequestDto requestDto, @PathVariable Long id){
        return talkReviewService.update(requestDto, id);
    }

    /**
     * TODO service 쪽으로 돌릴 수 있는 거 돌리기
     */

    @DeleteMapping("talk/{id}/comment")
    public void delete(@PathVariable Long id){
        talkReviewService.commentDelete(id);
    }
}
