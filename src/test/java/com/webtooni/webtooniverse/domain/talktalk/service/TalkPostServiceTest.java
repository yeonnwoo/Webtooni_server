package com.webtooni.webtooniverse.domain.talktalk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TalkPostServiceTest {

    @Mock
    TalkPostRepository talkPostRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TalkPostService talkPostService;


    @Test
    @DisplayName("TalkPost 저장하는 경우")
    void post() {
        //given
        TalkPostRequestDto talkPostRequestDto = TalkPostRequestDto.builder()
            .postTitle("talkPostTitle1")
            .postContent("talkPostContent1")
            .build();

        User user = User.builder()
            .userName("user1")
            .userScore(0)
            .build();

        TalkPost talkPost = TalkPost.builder().build();

        given(talkPostRepository.save(any())).willReturn(talkPost);
        given(userRepository.findById(any())).willReturn(java.util.Optional.ofNullable(user));

        //when
        TalkPostPostingResponseDto talkPostPostingResponseDto = talkPostService
            .post(talkPostRequestDto, user);

        //then
        assertThat(talkPostPostingResponseDto.getPostTitle())
            .isEqualTo("talkPostTitle1");
        assertThat(user.getUserScore()).isEqualTo(3);

    }

    @Test
    @DisplayName("게시글 수정 시 내용 변경 여부 확인")
    void updatePost() {
        //given
        TalkPostRequestDto talkPostRequestDto = TalkPostRequestDto.builder()
            .postTitle("newTalkPost1").postContent("newTalkPostContent1").build();
        TalkPost talkPost = TalkPost.builder().postTitle("talkPost1")
            .postContent("talkPostContent1").build();

        //when
        talkPost.update(talkPostRequestDto);

        //then
        assertThat(talkPost.getPostTitle()).isEqualTo("newTalkPost1");
        assertThat(talkPost.getPostContent()).isEqualTo("newTalkPostContent1");
    }

    @Test
    void deletePost() {
    }

    @Test
    void getOnePost() {
    }

    @Test
    void getPost() {
    }
}