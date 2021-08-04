package com.webtooni.webtooniverse.domain.talktalk.controller;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkReview;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkReviewRepository;
import com.webtooni.webtooniverse.domain.talktalk.service.TalkReviewService;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/talk/*")
@RequiredArgsConstructor
public class TalkReviewController {

    private final TalkReviewService talkReviewService;
    private final TalkReviewRepository talkReviewRepository;

    @PostMapping("/{id}/comment")
    public TalkReview postComment(@RequestBody TalkReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        TalkReview talkReview = talkReviewService.reviewPost(requestDto, user);
        return talkReview;
    }

    @GetMapping("/{id}/comment")
    public List<TalkReview> getComment(@PathVariable Long id) {
        List<TalkReview> talkReviewList = talkReviewRepository.findAllById(id);
        return talkReviewList;
    }

    @PutMapping("/{id}/comment")
    public TalkReview updateComment(@RequestBody TalkReviewRequestDto requestDto, @PathVariable Long id){
        TalkReview talkReview = talkReviewService.update(requestDto, id);
        return talkReview;
    }

    @DeleteMapping("/{id}/comment")
    public void delete(@PathVariable Long id){
        TalkReview talkReview = talkReviewRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 댓글을 찾을 수 없습니다")
        );
        talkReviewRepository.delete(talkReview);
    }
}
