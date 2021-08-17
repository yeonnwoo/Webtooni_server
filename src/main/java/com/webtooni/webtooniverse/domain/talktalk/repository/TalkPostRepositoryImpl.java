package com.webtooni.webtooniverse.domain.talktalk.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.webtooni.webtooniverse.domain.talktalk.dto.response.TalkPostPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.webtooni.webtooniverse.domain.talktalk.domain.QTalkPost.talkPost;
import static com.webtooni.webtooniverse.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class TalkPostRepositoryImpl implements TalkPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

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
