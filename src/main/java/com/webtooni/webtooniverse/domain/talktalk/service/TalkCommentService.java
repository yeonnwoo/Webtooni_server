package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.requset.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkCommentRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TalkCommentService {

    private final TalkCommentRepository talkCommentRepository;
    private final TalkPostRepository talkPostRepository;

    public TalkCommentPostingResponseDto commentPost(TalkCommentRequestDto requestDto, User user, Long id) {
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        talkPost.updateTalkCommentNum(1);
        TalkBoardComment talkBoardComment = new TalkBoardComment(requestDto, user, talkPost);
        talkCommentRepository.save(talkBoardComment);
        return new TalkCommentPostingResponseDto(talkBoardComment);
    }

    public TalkResponseDto commentDelete(Long id){
        TalkBoardComment talkBoardComment = talkCommentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        TalkPost talkPost = talkBoardComment.getTalkPost();
        talkPost.updateTalkCommentNum(-1);
        talkCommentRepository.delete(talkBoardComment);
        return new TalkResponseDto("삭제가 완료되었습니다.");
    }

    public TalkResponseDto update(TalkCommentRequestDto requestDto, Long id){
        TalkBoardComment talkBoardComment = talkCommentRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 댓글이 존재하지 않습니다")
        );
        talkBoardComment.update(requestDto);
        return new TalkResponseDto("수정이 완료되었습니다.");
    }
    /**
     * TODO 람다식 변경
     */
    public List<TalkCommentResponseDto> getComment(Long id) {
        List<TalkBoardComment> talkBoardComments = talkCommentRepository.findAllById(id);
        List<TalkCommentResponseDto> AllComments = talkBoardComments.stream()
                .map(TalkCommentResponseDto::new)
                .collect(Collectors.toList());
        return AllComments;
    }
}
