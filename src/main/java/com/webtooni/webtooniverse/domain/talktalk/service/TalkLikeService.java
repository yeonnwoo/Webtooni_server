package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLike;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeStatus;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLikeStatus;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class TalkLikeService {

    private final TalkLikeRepository talkLikeRepository;
    private final TalkPostRepository talkPostRepository;

    /**
     * 게시글에 좋아요를 누른다.
     *
     * @param talkPostId 게시글 id
     * @return TalkResponseDto
     */
    public TalkResponseDto postLike(Long talkPostId, User user) {

        //게시물 조회
        TalkPost talkPost = talkPostRepository.findById(talkPostId).orElseThrow(
            () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );

        TalkLike talkLike = talkLikeRepository
            .findTalkLikeByTalkPostAndUser(talkPost, user);

        if (talkLike == null) {
            talkPost.updateLikeNum(+1);
            TalkLike newTalkLike = TalkLike.of(user, talkPost);
            talkLikeRepository.save(newTalkLike);
            return new TalkResponseDto("좋아요가 저장되었습니다.");

        } else if (talkLike.getTalkLikeStatus() == TalkLikeStatus.CANCEL) {
            talkPost.updateLikeNum(+1);
            talkLike.changeStatusLike();
            return new TalkResponseDto("좋아요가 저장되었습니다.");

        } else if (talkLike.getTalkLikeStatus() == TalkLikeStatus.LIKE) {
            talkPost.updateLikeNum(-1);
            talkLike.changeStatusCancel();
            return new TalkResponseDto("좋아요가 취소되었습니다.");
        }

        return new TalkResponseDto("알 수 없는 응답입니다.");
    }
}
