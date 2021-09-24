package com.webtooni.webtooniverse.domain.talktalk.service;

import static com.webtooni.webtooniverse.domain.talktalk.service.TalkPostService.TALK_POST_POINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLikeStatus;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TalkPostServiceTest {

    @Mock
    TalkLikeRepository talkLikeRepository;

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
    @DisplayName("TalkPost 삭제 시 유저 점수 감소")
    void deletePost() {
        //given
        User user = User.builder().userScore(10).build();

        //when
        user.addUserScore(-3);

        //then
        assertThat(user.getUserScore()).isEqualTo(7);
    }

    @Nested
    @DisplayName("게시글 상세페이지 조회")
    class getOnePost{
        @Test
        @DisplayName("게시글 내용 일치 여부 확인")
        void getRightTalkPost() {
            //given
            User user = User.builder().userScore(10).build();
            TalkPost talkPost = TalkPost.builder().postTitle("talkPostTitle").user(user).build();
            TalkLike talkLike = new TalkLike(user, talkPost, TalkLikeStatus.LIKE);

            given(talkPostRepository.findById(any())).willReturn(
                java.util.Optional.ofNullable(talkPost));

            given(talkLikeRepository.findTalkLikeByTalkPostAndUser(talkPost, user))
                .willReturn(talkLike);

            //when
            TalkPostResponseDto onePost = talkPostService.getOnePost(1L, user);

            //then
            assertThat(onePost.getPostTitle()).isEqualTo("talkPostTitle");
        }

        @Test
        @DisplayName("user가 해당 게시글에 좋아요 누른 경우")
        void isLiked() {
            //given
            User user = User.builder().userScore(10).build();
            TalkPost talkPost = TalkPost.builder().postTitle("talkPostTitle").user(user).build();
            TalkLike talkLike = new TalkLike(user, talkPost, TalkLikeStatus.LIKE);

            given(talkPostRepository.findById(any())).willReturn(
                java.util.Optional.ofNullable(talkPost));

            given(talkLikeRepository.findTalkLikeByTalkPostAndUser(talkPost, user))
                .willReturn(talkLike);

            given(talkLikeRepository.existsByTalkPostAndUser(talkPost, user)).willReturn(true);

            //when
            TalkPostResponseDto onePost = talkPostService.getOnePost(1L, user);

            //then
            assertThat(onePost.isILike()).isEqualTo(true);
        }

        @Test
        @DisplayName("user가 해당 게시글에 좋아요 누르지 않은 경우")
        void isUnliked() {
            //given
            User user = User.builder().userScore(10).build();
            TalkPost talkPost = TalkPost.builder().postTitle("talkPostTitle").user(user).build();
            TalkLike talkLike = new TalkLike(user, talkPost, TalkLikeStatus.CANCEL);

            given(talkPostRepository.findById(any())).willReturn(
                java.util.Optional.ofNullable(talkPost));

            given(talkLikeRepository.findTalkLikeByTalkPostAndUser(talkPost, user))
                .willReturn(talkLike);

            given(talkLikeRepository.existsByTalkPostAndUser(talkPost, user)).willReturn(false);

            //when
            TalkPostResponseDto onePost = talkPostService.getOnePost(1L, user);

            //then
            assertThat(onePost.isILike()).isEqualTo(false);
        }

    }



    @Test
    void getPost() {
    }
}