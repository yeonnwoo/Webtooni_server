package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.AllTalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public TalkResponseDto updatePost(Long id, TalkPostRequestDto talkPostRequestDto){
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        talkPost.update(talkPostRequestDto);
        return new TalkResponseDto("수정이 완료되었습니다.");
    }

    public TalkResponseDto deletePost(Long id){
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        talkPostRepository.delete(talkPost);
        return new TalkResponseDto("삭제가 완료되었습니다.");
    }

    public TalkPostResponseDto getOnePost(Long id){
        TalkPost talkPost =  talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 포스팅이 존재하지 않습니다.")
        );
        return new TalkPostResponseDto(talkPost);
    }

    public AllTalkPostPageResponseDto getPost(int pageNumber, int size){
        Pageable pageable = PageRequest.of(pageNumber - 1, size);
        List<TalkPostPageResponseDto> posts = talkPostRepository.findAllTalkPost(pageable);
        long postCount = talkPostRepository.count();

        return new AllTalkPostPageResponseDto(posts, postCount);
    }

}
