package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkCommentRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkCommentResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkCommentRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class TalkCommentService {

    private final TalkCommentRepository talkCommentRepository;
    private final TalkPostRepository talkPostRepository;
    private final UserRepository userRepository;

    /**
     * 댓글을 작성합니다.
     * @param requestDto 댓글 내용을담은 dto
     * @param user 유저 객체
     * @param id 게시글 id
     * @return 게시글 내용을 담은 dto
     */
    public TalkCommentPostingResponseDto commentPost(TalkCommentRequestDto requestDto, User user,
        Long id) {
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        talkPost.updateTalkCommentNum(1);
        TalkBoardComment talkBoardComment = new TalkBoardComment(requestDto, user, talkPost);
        talkCommentRepository.save(talkBoardComment);
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
        );
        findUser.addUserScore(1);
        return new TalkCommentPostingResponseDto(talkBoardComment);
    }

    /**
     * 댓글을 삭제합니다.
     * @param id 게시글 id
     * @param user 유저 객체
     */
    public void commentDelete(Long id, User user) {
        TalkBoardComment talkBoardComment = getTalkBoardComment(id);
        TalkPost talkPost = talkBoardComment.getTalkPost();
        talkPost.updateTalkCommentNum(-1);
        talkCommentRepository.delete(talkBoardComment);
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
        );
        findUser.addUserScore(-1);
    }

    /**
     * 댓글 내용을 수정합니다.
     * @param requestDto 수정할 댓글 내용
     * @param id 게시글 id
     */
    public void update(TalkCommentRequestDto requestDto, Long id) {
        TalkBoardComment talkBoardComment = getTalkBoardComment(id);
        talkBoardComment.update(requestDto);
    }

    //

    /**
     * 게시글 별 댓글 리스트를 조회합니다. id:게시글 id
     * @param id 게시글 id
     * @return 댓글 내용을 담은 dto list
     */
    public List<TalkCommentResponseDto> getComment(Long id) {
        List<TalkBoardComment> talkBoardComments = talkCommentRepository
            .findAllCommentByBoardId(id);
        return talkBoardComments.stream()
            .map(TalkCommentResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * 댓글id로 댓글을 조회합니다.
     * @param id 댓글 id
     * @return 댓글 객체
     */
    private TalkBoardComment getTalkBoardComment(Long id) {
        return talkCommentRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
        );
    }
}
