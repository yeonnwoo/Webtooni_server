package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkCommentRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TalkCommentService {

    private final TalkCommentRepository talkCommentRepository;
    private final TalkPostRepository talkPostRepository;

    public TalkCommentPostingResponseDto commentPost(TalkCommentRequestDto requestDto, User user,
        Long id) {
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        talkPost.updateTalkCommentNum(1);
        TalkBoardComment talkBoardComment = new TalkBoardComment(requestDto, user, talkPost);
        talkCommentRepository.save(talkBoardComment);
        return new TalkCommentPostingResponseDto(talkBoardComment);
    }

    public void commentDelete(Long id) {
        TalkBoardComment talkBoardComment = getTalkBoardComment(id);
        TalkPost talkPost = talkBoardComment.getTalkPost();
        talkPost.updateTalkCommentNum(-1);
        talkCommentRepository.delete(talkBoardComment);
    }

    public void update(TalkCommentRequestDto requestDto, Long id) {
        TalkBoardComment talkBoardComment = getTalkBoardComment(id);
        talkBoardComment.update(requestDto);
    }

    //게시글 별 댓글 리스트 불러오기 id:게시글 id
    public List<TalkCommentResponseDto> getComment(Long id) {
        List<TalkBoardComment> talkBoardComments = talkCommentRepository
            .findAllCommentByBoardId(id);
        return talkBoardComments.stream()
            .map(TalkCommentResponseDto::new)
            .collect(Collectors.toList());
    }

    private TalkBoardComment getTalkBoardComment(Long id) {
        return talkCommentRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
        );
    }
}
