package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkBoardComment;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewGetRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.TalkReviewRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkReviewRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TalkReviewService {

    private final TalkReviewRepository talkReviewRepository;
    private final TalkPostRepository talkPostRepository;

    @Transactional
    public TalkBoardComment reviewPost(Long id, TalkReviewRequestDto requestDto, User user) {
        TalkBoardComment talkBoardComment = new TalkBoardComment(requestDto, user);
        talkReviewRepository.save(talkBoardComment);
        TalkPost talkPost = talkPostRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        talkPost.updateTalkCommentNum(1);
        return talkBoardComment;
    }


    public TalkBoardComment update(TalkReviewRequestDto requestDto, Long id){
        TalkBoardComment talkBoardComment = talkReviewRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 댓글이 존재하지 않습니다")
        );
        talkBoardComment.update(requestDto);
        return talkBoardComment;
    }
    /**
     * TODO 람다식 변경
     */
    public List<TalkReviewGetRequestDto> getComment(Long id) {
        List<TalkReviewGetRequestDto> sendingList = new ArrayList<>();
        List<TalkBoardComment> talkBoardComments = talkReviewRepository.findAllById(id);
        for (TalkBoardComment talkBoardComment : talkBoardComments) {
            TalkReviewGetRequestDto talkReviewGetRequestDto = new TalkReviewGetRequestDto(talkBoardComment);
            sendingList.add(talkReviewGetRequestDto);
        }
        return sendingList;
    }
}
