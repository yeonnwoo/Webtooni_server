package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.*;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGenre;
import com.webtooni.webtooniverse.domain.user.domain.UserGenreRepository;
import com.webtooni.webtooniverse.domain.user.dto.response.UserGenreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class TalkPostService {

    private final TalkPostRepository talkPostRepository;
    private final UserGenreRepository userGenreRepository;
    private final TalkLikeRepository talkLikeRepository;

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
        //해당 게시글 정보 찾기
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        talkPostRepository.delete(talkPost);
        return new TalkResponseDto("삭제가 완료되었습니다.");
    }

    public TalkPostResponseDto getOnePost(Long id, User user){
        // 해당 게시글 정보 찾기
        TalkPost talkPost =  talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 포스팅이 존재하지 않습니다.")
        );

        // 해당 게시물이 내가 좋아요를 누른 웹툰인지 아닌지 확인
        boolean exists = talkLikeRepository.existsByTalkPostAndUser(talkPost, user);

        // 해당 유저가 좋아요한 게시글 리스트
        List<TalkLike> likeList = talkLikeRepository.findAllByUser(user);
        List<TalkLikeListResponseDto> likeListDto = likeList.stream()
                .map(TalkLikeListResponseDto::new)
                .collect(Collectors.toList());

        return new TalkPostResponseDto(talkPost, exists, likeListDto);
    }

    public AllTalkPostPageLikeResponseDto getPost(int page, int size, User user){
        Pageable pageable = PageRequest.of(page - 1, size);
        List<TalkPostPageResponseDto> posts = talkPostRepository.findAllTalkPost(pageable);
        long postCount = talkPostRepository.count();
        AllTalkPostPageResponseDto AllPostDto = new AllTalkPostPageResponseDto(posts, postCount);

        List<TalkLike> likeList = talkLikeRepository.findAllByUser(user);
        List<TalkLikeListResponseDto> likeListDto = likeList.stream()
                .map(TalkLikeListResponseDto::new)
                .collect(Collectors.toList());

        return new AllTalkPostPageLikeResponseDto(AllPostDto, likeListDto);
    }

}
