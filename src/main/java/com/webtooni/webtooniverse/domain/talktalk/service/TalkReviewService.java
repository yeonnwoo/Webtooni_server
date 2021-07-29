package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkReview;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class TalkReviewService {

    private final TalkReviewRepository talkReviewRepository;

    @Transactional
    public TalkReview reviewPost(TalkReviewRequestDto requestDto, User user) {
        TalkReview talkReview = new TalkReview(requestDto, user);
        talkReviewRepository.save(talkReview);
        return talkReview;
    }

    @Transactional
    public TalkReview update(TalkReviewRequestDto requestDto, Long id){
        TalkReview talkReview = talkReviewRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 댓글이 존재하지 않습니다")
        );
        talkReview.update(requestDto);
        return talkReview;
    }
}
