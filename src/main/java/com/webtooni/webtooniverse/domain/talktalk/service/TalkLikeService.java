package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class TalkLikeService {

    private final TalkLikeRepository talkLikeRepository;
    private final TalkPostRepository talkPostRepository;

    public TalkResponseDto postLike(Long talkPostId) {

        TalkPost talkPost = talkPostRepository.findById(talkPostId).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        boolean isExist = talkLikeRepository.existsByTalkPostAndUser(talkPost, user);
        if (isExist) {
            talkLikeRepository.deleteByTalkPostAndUser(talkPost, user);
            talkPost.updateLikeNum(-1);
            return new TalkResponseDto("좋아요가 취소되었습니다.");
        }
        else {
            TalkLike talkLike = new TalkLike(talkPost, user);
            talkLikeRepository.save(talkLike);
            talkPost.updateLikeNum(+1);
            return new TalkResponseDto("좋아요가 저장되었습니다.");
        }
    }


}
