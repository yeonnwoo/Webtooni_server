package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageableResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * TODO 람다식으로 변경 확인
     */
    public TalkPostPageableResponseDto getPost(int pageNumber, int size) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1000);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC,"createDate"));
        Page<TalkPost> talkPosts = talkPostRepository.findAllByCreateDateAfterOrderByCreateDateDesc(localDateTime, pageRequest);
        List<TalkPost> postList = talkPosts.getContent();
        long totalElements = talkPosts.getTotalElements();

        return new TalkPostPageableResponseDto(postList, totalElements);
    }
}
