package com.webtooni.webtooniverse.domain.talktalk.service;

import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLike;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkLikeStatus;
import com.webtooni.webtooniverse.domain.talktalk.domain.TalkPost;
import com.webtooni.webtooniverse.domain.talktalk.dto.request.TalkPostRequestDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.AllTalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPostingResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostResponseDto;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkCommentRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkLikeRepository;
import com.webtooni.webtooniverse.domain.talktalk.repository.TalkPostRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
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
    private final TalkCommentRepository talkCommentRepository;
    private final UserRepository userRepository;


    /**
     * 게시글을 작성합니다.
     *
     * @param requestDto 게시글 제목,내용
     * @param user       user
     * @return TalkPostPostingResponseDto
     */
    public TalkPostPostingResponseDto post(TalkPostRequestDto requestDto, User user) {
        TalkPost talkPost = new TalkPost(requestDto, user);
        talkPostRepository.save(talkPost);
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
        );
        findUser.addUserScore(3);
        return new TalkPostPostingResponseDto(talkPost);
    }

    /**
     * 게시글을 수정합니다.
     *
     * @param id                 postId
     * @param talkPostRequestDto postTitle,postContent
     */
    public void updatePost(Long id, TalkPostRequestDto talkPostRequestDto) {
        TalkPost talkPost = getTalkPost(id);
        talkPost.update(talkPostRequestDto);
    }

    /**
     * 게시글을 삭제합니다.
     *
     * @param id postId
     */
    public void deletePost(Long id, User user) {

        TalkPost talkPost = getTalkPost(id);

        //유저 점수 update
        User findUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new NullPointerException("해당 유저를 찾을 수 없습니다.")
        );
        findUser.addUserScore(-3);

        //talkBoradLike,comment도 함께 삭제
        talkCommentRepository.deleteAllByTalkPost(talkPost);
        talkLikeRepository.deleteAllByTalkPost(talkPost);
        talkPostRepository.deleteById(id);
    }

    /**
     * 게시글 상세 페이지를 조회합니다.
     *
     * @param id          postId
     * @param userDetails user
     * @return TalkPostResponseDto
     */
    public TalkPostResponseDto getOnePost(Long id, UserDetailsImpl userDetails) {
        // 해당 게시글 정보 찾기
        TalkPost talkPost = getTalkPost(id);
        boolean exists;
        if (userDetails == null) {
            exists = false;
        } else {
            User user = userDetails.getUser();
            // 해당 게시물이 내가 좋아요를 누른 게시글인지 아닌지 확인
            TalkLike talkLike = talkLikeRepository
                .findTalkLikeByTalkPostAndUser(talkPost, user);

            boolean b = talkLikeRepository.existsByTalkPostAndUser(talkPost, user);

            if (b) {
                exists = talkLike.getTalkLikeStatus() == TalkLikeStatus.LIKE;
            } else {
                exists = false;
            }
        }
        return new TalkPostResponseDto(talkPost, exists);
    }


    /**
     * 톡톡 게시글 리스트를 조회합니다.
     *
     * @param page page
     * @param size size
     * @return AllTalkPostPageResponseDto
     */
    public AllTalkPostPageResponseDto getPost(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<TalkPostPageResponseDto> posts = talkPostRepository.findAllTalkPost(pageable);
        long postCount = talkPostRepository.count();

        return new AllTalkPostPageResponseDto(posts, postCount);
    }

    /**
     * 게시글 id로 게시글을 조회합니다.
     *
     * @param id 게시글 id
     * @return 게시글 객체
     */
    private TalkPost getTalkPost(Long id) {
        return talkPostRepository.findById(id).orElseThrow(
            () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
    }

}
