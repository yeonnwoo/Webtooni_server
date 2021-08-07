package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TalkPostService {

    private final TalkPostRepository talkPostRepository;

    public TalkPost post(TalkPostRequestDto requestDto, User user){
        TalkPost talkPost = new TalkPost(requestDto, user);
        talkPostRepository.save(talkPost);
        return talkPost;
    }



    public Long updatePost(Long id, TalkPostRequestDto talkPostRequestDto){
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        talkPost.update(talkPostRequestDto);
        return id;
    }

    /**
     * TODO 람다식으로 변경 확인
     */
    public List<TalkPostResponseDto> getPost() {
        List<TalkPostResponseDto> sendingList = new ArrayList<>();
        List<TalkPost> talkPosts = talkPostRepository.findAll();
        for (TalkPost talkPost : talkPosts) {
            TalkPostResponseDto talkPostResponseDto = new TalkPostResponseDto(talkPost);
            sendingList.add(talkPostResponseDto);
        }
        return sendingList;
    }
}
