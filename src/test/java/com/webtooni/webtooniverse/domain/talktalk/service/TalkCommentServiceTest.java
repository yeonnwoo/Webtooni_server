package com.webtooni.webtooniverse.domain.talktalk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkCommentRepository;
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
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class TalkCommentServiceTest {

    @Mock
    TalkPostRepository talkPostRepository;

    @Mock
    TalkCommentRepository talkCommentRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TalkCommentService talkCommentService;

    @Test
    @DisplayName("talkPost 댓글 작성 (댓글 내용 일치, user 점수 +1)")
    void commentPost() {
        //given
        TalkCommentRequestDto TCRD = new TalkCommentRequestDto("commentContent");
        User user = User.builder().userName("user").userScore(0).build();
        TalkPost talkPost = TalkPost.builder().postTitle("talkPost").talkCommentCount(0).build();
        given(talkPostRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(talkPost));
        given(talkCommentRepository.save(any())).willReturn(null);
        given(userRepository.findById(any())).willReturn(java.util.Optional.ofNullable(user));

        //when
        TalkCommentPostingResponseDto talkCommentPostingResponseDto = talkCommentService
            .commentPost(TCRD, user, 1L);

        //then
        assertThat(talkCommentPostingResponseDto.getCommentContent()).isEqualTo("commentContent");
        assertThat(talkPost.getTalkCommentCount()).isEqualTo(1);
        assertThat(user.getUserScore()).isEqualTo(1);

    }

    @Test
    @DisplayName("talkPost 댓글 삭제 (")
    void commentDelete() {
//        TalkPost talkPost = TalkPost.builder().build();
//        User user = User.builder().build();
//        TalkBoardComment talkBoardComment = TalkBoardComment.builder().talkPost(talkPost).user(user).build();
//        given(talkCommentRepository.delete(talkBoardComment)).willReturn(null);
//        given(userRepository.findById(any())).willReturn(java.util.Optional.ofNullable(user));
//        given(talkCommentRepository.findById(any())).willReturn(
//            java.util.Optional.ofNullable(talkBoardComment));
    }

    @Test
    void update() {
    }

    @Test
    void getComment() {
    }
}