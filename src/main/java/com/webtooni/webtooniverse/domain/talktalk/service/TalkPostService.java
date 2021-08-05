package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostGetRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TalkPostService {

    private final TalkPostRepository talkPostRepository;

    @Transactional
    public TalkPost post(TalkPostRequestDto requestDto, User user){
        TalkPost talkPost = new TalkPost(requestDto, user);
        talkPostRepository.save(talkPost);
        return talkPost;
    }

    @Transactional
    public Long updatePost(Long id, TalkPostRequestDto talkPostRequestDto){
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        talkPost.update(talkPostRequestDto);
        return id;
    }

//    public List<TalkPostGetRequestDto> getPost() {
//        List<TalkPostGetRequestDto> sendingList = new ArrayList<>();
//        List<TalkPost> talkPostList = talkPostRepository.findAll();
//        for (talkPost : talkPostList) {
//
//        }
//    }
}
