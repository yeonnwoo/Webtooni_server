package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkReview;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostGetRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewGetRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TalkReviewService {

    private final TalkReviewRepository talkReviewRepository;
    private final TalkPost talkPost;

    @Transactional
    public TalkReview reviewPost(TalkReviewRequestDto requestDto, User user) {
        TalkReview talkReview = new TalkReview(requestDto, user);
        talkReviewRepository.save(talkReview);
        talkPost.updateTalkCommentNum(1);
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
    public List<TalkReviewGetRequestDto> getComment(Long id) {
        List<TalkReviewGetRequestDto> sendingList = new ArrayList<>();
        List<TalkReview> talkReviews = talkReviewRepository.findAllById(id);
        for (TalkReview talkReview : talkReviews) {
            TalkReviewGetRequestDto talkReviewGetRequestDto = new TalkReviewGetRequestDto(talkReview);
            sendingList.add(talkReviewGetRequestDto);
        }
        return sendingList;
    }
}
