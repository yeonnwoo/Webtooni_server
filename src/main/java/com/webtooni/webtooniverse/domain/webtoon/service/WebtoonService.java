package com.webtooni.webtooniverse.domain.webtoon.service;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.myList.MyListRepository;
import com.webtooni.webtooniverse.domain.review.domain.Review;
import com.webtooni.webtooniverse.domain.review.domain.ReviewRepository;
import com.webtooni.webtooniverse.domain.review.dto.response.WebtoonDetailReviewResponseDto;
import com.webtooni.webtooniverse.domain.reviewLike.domain.ReviewLikeRepository;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoOnlyResponseDto;
import com.webtooni.webtooniverse.domain.user.security.UserDetailsImpl;
import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.BestReviewerWebtoonResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final MyListRepository myListRepository;
    private final UserRepository userRepository;

    //금주의 웹툰 평론가 추천
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoon() {
        User bestReviewer = webtoonRepository.findBestReviewer();
        if (bestReviewer == null) {
            throw new NullPointerException("리뷰를 작성한 유저가 없습니다.");
        }
        List<WebtoonAndGenreResponseDto> bestReviewerWebtoons = webtoonRepository
            .findBestReviewerWebtoon(bestReviewer);
        UserInfoOnlyResponseDto userInfoOnlyResponseDto = new UserInfoOnlyResponseDto(bestReviewer);
        return new BestReviewerWebtoonResponseDto(userInfoOnlyResponseDto, bestReviewerWebtoons);
    }

    //유저 관심 장르 중 랜덤 추천
    public List<WebtoonResponseDto> getForUserWebtoon(User user) {
        int howManyWebtoons = 5;
        List<Webtoon> userGenreWebtoons = webtoonRepository.findUserGenreWebtoon(user);
        Collections.shuffle(userGenreWebtoons);
        List<Webtoon> countedUserGenreWebtoons
            = IntStream.range(0, howManyWebtoons)
            .mapToObj(userGenreWebtoons::get)
            .collect(Collectors.toList());
        return countedUserGenreWebtoons.stream().map(WebtoonResponseDto::new)
            .collect(Collectors.toList());
    }

    //비슷한 취향을 가진 유저가 높게 평가한 작품 추천
    public List<WebtoonAndGenreResponseDto> getSimilarUserWebtoon(User user) {
        return webtoonRepository.findSimilarUserWebtoon(user);
    }

    //MD 추천
    public WebtoonResponseDto getMdWebtoon() {
        Webtoon webtoon = webtoonRepository.findById(1L).orElseThrow(
            () -> new NullPointerException("해당 id의 웹툰이 없습니다.")
        );
        return new WebtoonResponseDto(webtoon);
    }

    //완결 웹툰 추천
    public List<WebtoonAndGenreResponseDto> getFinishedWebtoon() {
        int howManyWebtoons = 5;
        List<WebtoonAndGenreResponseDto> finishedWebtoons = webtoonRepository.findFinishedWebtoon();
        Collections.shuffle(finishedWebtoons);
        return IntStream.range(0, howManyWebtoons).mapToObj(finishedWebtoons::get)
            .collect(Collectors.toList());
    }

    //이번달 웹투니버스 종합순위
    public List<WebtoonAndGenreResponseDto> getMonthTotalRank() {
        return webtoonRepository.getTotalRank();
    }

    //웹투니버스 네이버 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthNaverRank() {
        List<Webtoon> monthNaverRank = webtoonRepository.getNaverRank();
        return monthNaverRank
            .stream()
            .map(PlatformRankResponseDto::new)
            .collect(Collectors.toList());
    }

    //웹투니버스 카카오 웹툰 Top10
    public List<PlatformRankResponseDto> getMonthKakaoRank() {
        List<Webtoon> monthKakaoRank = webtoonRepository.getKakaoRank();
        return monthKakaoRank
            .stream()
            .map(PlatformRankResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * 웹툰 상세 정보와 review List를 조회한다.
     *
     * @param id          toonId
     * @param userDetails User
     * @return WebtoonDetailDto
     */
    public WebtoonDetailDto getDetailAndReviewList(Long id, UserDetailsImpl userDetails) {

        List<Long> reviewIdListByUser;
        boolean exists;

        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 웹툰의 id는 존재하지 않습니다.")
        );

        List<Genre> webtoonGenre = webtoonRepository.findWebToonGenre(webtoon);
        List<String> genreList = webtoonGenre.stream().map(Genre::getGenreType)
            .collect(Collectors.toList());
        List<Review> reviewList = reviewRepository.findReviewByWebToonId(id);

        List<WebtoonDetailReviewResponseDto> ReviewDtoList = reviewList.stream()
            .map(WebtoonDetailReviewResponseDto::new)
            .collect(Collectors.toList());

        if (userDetails != null) {
            User user = userDetails.getUser();
            reviewIdListByUser = reviewLikeRepository.findReviewIdListByUser(user.getId());

            //내가 찜한 웹툰인지 아닌지
            exists = myListRepository.existsById(user.getId(), id);

        } else {
            reviewIdListByUser = null;
            exists = false;
        }
        return new WebtoonDetailDto(reviewIdListByUser, exists, webtoon, genreList, ReviewDtoList);
    }

    /**
     * 비슷한 장르의 웹툰을 추천한다.
     *
     * @param id toonId
     * @return List<SimilarGenreToonDto>
     */
    public List<SimilarGenreToonDto> getSimilarGenre(Long id) {

        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 id의 웹툰은 존재하지 않습니다.")
        );
        List<Genre> genres = webtoonRepository.findWebToonGenre(webtoon);

        List<SimilarGenreToonDto> similarGenreToonList = new ArrayList<>();
        genres.stream()
            .map(g -> webtoonRepository.findSimilarWebtoonByGenre(g.getGenreType(), webtoon))
            .forEach(similarGenreToonList::addAll);

        return similarGenreToonList;
    }

    public List<WebtoonResponseDto> getMyListWebtoons(String userName) {
        List<Webtoon> myListWebtoon = webtoonRepository.findMyListWebtoon(userName);
        return myListWebtoon.stream()
            .map(WebtoonResponseDto::new)
            .collect(Collectors.toList());
    }

    public List<WebtoonResponseDto> getUnreviewdList() {
        List<Webtoon> Webtoons = webtoonRepository.findTop10ByReviewCountLessThanEqual(1);
        return Webtoons.stream()
            .map(WebtoonResponseDto::new)
            .collect(Collectors.toList());
    }

    public List<WebtoonAndGenreResponseDto> getSearchedWebtoon(String keyword) {
        List<WebtoonAndGenreResponseDto> webtoons = webtoonRepository
            .findSearchedWebtoon(keyword.substring(0, 1));
        String trimKeyword = keyword.replace(" ", "");
        List<WebtoonAndGenreResponseDto> webtoonResponseDtos = new ArrayList<>();
        for (WebtoonAndGenreResponseDto webtoon : webtoons) {
            if (webtoon.getToonTitle().replace(" ", "").contains(trimKeyword)) {
                webtoonResponseDtos.add(webtoon);
            }
        }
        return webtoonResponseDtos;
    }

    public User getUser(String user) {
        return userRepository.findByUserName(user).orElseThrow(
            ()->new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
    }
}
