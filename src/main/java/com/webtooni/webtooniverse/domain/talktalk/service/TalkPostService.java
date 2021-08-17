package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.AllTalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class TalkPostService {

  private final TalkPostRepository talkPostRepository;
  private final TalkLikeRepository talkLikeRepository;

  public TalkPostPostingResponseDto post(TalkPostRequestDto requestDto, User user) {
    TalkPost talkPost = new TalkPost(requestDto, user);
    talkPostRepository.save(talkPost);
    return new TalkPostPostingResponseDto(talkPost);
  }

  public void updatePost(Long id, TalkPostRequestDto talkPostRequestDto) {
    TalkPost talkPost = getTalkPost(id);
    talkPost.update(talkPostRequestDto);
  }

  public void deletePost(Long id) {
    //해당 게시글 정보 찾기
    TalkPost talkPost = getTalkPost(id);
    talkPostRepository.delete(talkPost);
  }

  public TalkPostResponseDto getOnePost(Long id, UserDetailsImpl userDetails) {
    // 해당 게시글 정보 찾기
    TalkPost talkPost = getTalkPost(id);
    boolean exists;
    if (userDetails == null) {
      exists = false;
    } else {
      User user = userDetails.getUser();
      // 해당 게시물이 내가 좋아요를 누른 웹툰인지 아닌지 확인
      exists = talkLikeRepository.existsByTalkPostAndUser(talkPost, user);
    }
    return new TalkPostResponseDto(talkPost, exists);
  }

  public AllTalkPostPageResponseDto getPost(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    List<TalkPostPageResponseDto> posts = talkPostRepository.findAllTalkPost(pageable);
    long postCount = talkPostRepository.count();

    return new AllTalkPostPageResponseDto(posts, postCount);
  }

  private TalkPost getTalkPost(Long id) {
    return talkPostRepository.findById(id).orElseThrow(
        () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
    );
  }

}
