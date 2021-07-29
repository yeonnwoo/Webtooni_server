package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkLikeResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import lombok.RequiredArgsConstructor;


import javax.transaction.Transactional;


@RequiredArgsConstructor
public class TalkLikeService {

    private final TalkLikeRepository talkLikeRepository;
    private final TalkPostRepository talkPostRepository;


    @Transactional
    public TalkLikeResponseDto postLike(Long talkPostId, Long userId) {

        TalkPost talkPost = talkPostRepository.findById(talkPostId).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        boolean isExist = talkLikeRepository.existsByTalkPostIdAndUserId(talkPostId, userId);
        if (isExist) {
            talkLikeRepository.deleteByTalkPostIdAndUserId(talkPostId, userId);
            talkPost.updateLikeNum(-1);
            return new TalkLikeResponseDto("좋아요가 취소되었습니다.");
        }
        else {
            TalkLike talkLike = new TalkLike(talkPostId, userId);
            talkLikeRepository.save(talkLike);
            talkPost.updateLikeNum(+1);
            return new TalkLikeResponseDto("좋아요가 저장되었습니다.");
        }
    }


}
