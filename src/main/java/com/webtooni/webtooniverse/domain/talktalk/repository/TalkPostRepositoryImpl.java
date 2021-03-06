package com.webtooni.webtooniverse.domain.talktalk.repository;

import static com.webtooni.webtooniverse.domain.talktalk.domain.QTalkPost.talkPost;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class TalkPostRepositoryImpl implements TalkPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 페이지네이션된 게시글 목록을 조회합니다.
     * @param pageable pageable params
     * @return 조회된 게시글 목록을 담은 dto
     */
    //모든 톡톡 게시글 불러오기
    public List<TalkPostPageResponseDto> findAllTalkPost(Pageable pageable) {

        return jpaQueryFactory
            .select(Projections.constructor(TalkPostPageResponseDto.class,
                talkPost.id,
                talkPost.postTitle,
                talkPost.postContent,
                talkPost.user.id,
                talkPost.user.userImg,
                talkPost.user.userName,
                talkPost.user.userGrade,
                talkPost.createDate,
                talkPost.likeNum,
                talkPost.talkCommentCount))
            .from(talkPost)
            .innerJoin(talkPost.user, user)
            .orderBy(talkPost.createDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }
}
