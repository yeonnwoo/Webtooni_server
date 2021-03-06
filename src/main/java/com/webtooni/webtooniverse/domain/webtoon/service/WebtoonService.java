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
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankListResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.PlatformRankResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.RankTotalResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.SimilarGenreToonDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonAndGenreResponseDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonDetailDto;
import com.webtooni.webtooniverse.domain.webtoon.dto.response.WebtoonResponseDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
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
    private final RedisTemplate redisTemplate;

    /**
     * ????????? ?????? ????????? ?????? ????????? ???????????????.
     * @return ?????? ????????? ????????? ?????? ?????? ????????? ?????? dto
     */
    public BestReviewerWebtoonResponseDto getBestReviewerWebtoon() {

        final ValueOperations<String, BestReviewerWebtoonResponseDto> valueOperation = redisTemplate
            .opsForValue();
        BestReviewerWebtoonResponseDto cacheBestReviewerWebtoonResponseDto = valueOperation
            .get("bestReviewerWebtoon");

        if (cacheBestReviewerWebtoonResponseDto == null) {
            User bestReviewer = webtoonRepository.findBestReviewer();
            if (bestReviewer == null) {
                throw new NullPointerException("????????? ????????? ????????? ????????????.");
            }
            List<WebtoonAndGenreResponseDto> bestReviewerWebtoons = webtoonRepository
                .findBestReviewerWebtoon(bestReviewer);
            UserInfoOnlyResponseDto userInfoOnlyResponseDto = new UserInfoOnlyResponseDto(
                bestReviewer);
            BestReviewerWebtoonResponseDto bestReviewerWebtoonResponseDto = new BestReviewerWebtoonResponseDto(
                userInfoOnlyResponseDto, bestReviewerWebtoons);
            valueOperation.set("bestReviewerWebtoon", bestReviewerWebtoonResponseDto);
            return bestReviewerWebtoonResponseDto;
        }
        return cacheBestReviewerWebtoonResponseDto;
    }

    /**
     * ????????? ?????? ?????? ????????? ?????? ????????? ???????????????.
     * @param user ?????? ??????
     * @return ?????? ????????? ?????? ?????? ????????? ?????? dto list
     */
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

    /**
     * ????????? ????????? ?????? ????????? ?????? ????????? ???????????????.
     * @param user ?????? ??????
     * @return ????????? ????????? ?????? ????????? ?????? ????????? ?????? ????????? ?????? dto list
     */
    public List<WebtoonAndGenreResponseDto> getSimilarUserWebtoon(User user) {
        return webtoonRepository.findSimilarUserWebtoon(user);
    }

    /**
     * md ?????? ?????? ???????????????.
     * @return ?????? ?????? ????????? ?????? dto
     */
    public WebtoonResponseDto getMdWebtoon() {
        Webtoon webtoon = webtoonRepository.findById(1L).orElseThrow(
            () -> new NullPointerException("?????? id??? ????????? ????????????.")
        );
        return new WebtoonResponseDto(webtoon);
    }

    /**
     * ?????? ?????? ????????? ???????????????.
     * @return ?????? ?????? ?????? ????????? ?????? dto list
     */
    public List<WebtoonAndGenreResponseDto> getFinishedWebtoon() {
        int howManyWebtoons = 5;
        List<WebtoonAndGenreResponseDto> finishedWebtoons = webtoonRepository.findFinishedWebtoon();
        Collections.shuffle(finishedWebtoons);
        return IntStream.range(0, howManyWebtoons).mapToObj(finishedWebtoons::get)
            .collect(Collectors.toList());
    }

    /**
     * ?????? ??? ??????????????? ?????? ?????? ????????? ???????????????.
     * @return ?????? ?????? ?????? ????????? ????????? ?????? dto list
     */
    public Set<RankTotalResponseDto> getWeeklyTotalRank() {
        final ZSetOperations<String, RankTotalResponseDto> zSetOperations = redisTemplate
            .opsForZSet();
        Set<RankTotalResponseDto> monthTotalRankV2 = zSetOperations
            .reverseRange("weeklyTotalRankV2", 1, 20);

        if (monthTotalRankV2.size() == 0) {
            List<RankTotalResponseDto> totalRank = webtoonRepository.getTotalRank();
            for (RankTotalResponseDto rankTotalResponseDto : totalRank) {
                zSetOperations.add("weeklyTotalRankV2", rankTotalResponseDto,
                    rankTotalResponseDto.getWeeklyAvgPoint());
            }
            return zSetOperations
                .reverseRange("weeklyTotalRankV2", 1, 20);
        }
        return monthTotalRankV2;
    }

    /**
     * ????????? top10 ?????? ????????? ???????????????.
     * @return ????????? top10 ?????? dto list
     */
    public List<PlatformRankResponseDto> getNaverRank() {
        final ValueOperations<String, PlatformRankListResponseDto> valueOperation = redisTemplate
            .opsForValue();
        PlatformRankListResponseDto cachePlatformRankListResponseDto = valueOperation
            .get("naverRank");

        if (cachePlatformRankListResponseDto == null) {
            List<Webtoon> monthNaverRank = webtoonRepository.getNaverRank();
            List<PlatformRankResponseDto> platformRankResponseDtos = monthNaverRank
                .stream()
                .map(PlatformRankResponseDto::new)
                .collect(Collectors.toList());
            valueOperation
                .set("naverRank", new PlatformRankListResponseDto(platformRankResponseDtos));
            return platformRankResponseDtos;
        }
        return cachePlatformRankListResponseDto.getPlatformRankResponseDtoList();
    }

    /**
     * ????????? top10 ?????? ????????? ???????????????.
     * @return ????????? top10 ?????? dto list
     */
    public List<PlatformRankResponseDto> getKakaoRank() {
        final ValueOperations<String, PlatformRankListResponseDto> valueOperation
            = redisTemplate.opsForValue();
        PlatformRankListResponseDto cachePlatformRankListResponseDto = valueOperation
            .get("kakaoRank");

        if (cachePlatformRankListResponseDto == null) {
            List<Webtoon> monthKaKaoRank = webtoonRepository.getKakaoRank();
            List<PlatformRankResponseDto> platformRankResponseDtos = monthKaKaoRank
                .stream()
                .map(PlatformRankResponseDto::new)
                .collect(Collectors.toList());
            valueOperation
                .set("kakaoRank", new PlatformRankListResponseDto(platformRankResponseDtos));
            return platformRankResponseDtos;
        }
        return cachePlatformRankListResponseDto.getPlatformRankResponseDtoList();
    }

    /**
     * ?????? ?????? ????????? review List??? ????????????.
     *
     * @param id          toonId
     * @param userDetails User
     * @return WebtoonDetailDto
     */
    public WebtoonDetailDto getDetailAndReviewList(Long id, UserDetailsImpl userDetails) {

        List<Long> reviewIdListByUser;
        boolean exists;

        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("?????? ????????? id??? ???????????? ????????????.")
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

            //?????? ?????? ???????????? ?????????
            exists = myListRepository.existsById(user.getId(), id);

        } else {
            reviewIdListByUser = null;
            exists = false;
        }
        return new WebtoonDetailDto(reviewIdListByUser, exists, webtoon, genreList, ReviewDtoList);
    }

    /**
     * ????????? ????????? ????????? ????????????.
     *
     * @param id toonId
     * @return List<SimilarGenreToonDto>
     */
    public List<SimilarGenreToonDto> getSimilarGenre(Long id) {

        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("?????? id??? ????????? ???????????? ????????????.")
        );
        List<Genre> genres = webtoonRepository.findWebToonGenre(webtoon);

        List<SimilarGenreToonDto> similarGenreToonList = new ArrayList<>();
        genres.stream()
            .map(g -> webtoonRepository.findSimilarWebtoonByGenre(g.getGenreType(), webtoon))
            .forEach(similarGenreToonList::addAll);

        return similarGenreToonList;
    }

    /**
     * ??????????????? ????????? ????????? ???????????????.
     * @param userName ?????? ??????
     * @return ??????????????? ?????? ?????? ????????? ?????? dto list
     */
    public List<WebtoonAndGenreResponseDto> getMyListWebtoons(String userName) {
        return webtoonRepository.findMyListWebtoon(userName);
    }

    /**
     * ????????? ???????????? ?????? ?????? ????????? ???????????????.
     * @return ?????? ???????????? ?????? ?????? ????????? ?????? dto list
     */
    public List<WebtoonResponseDto> getUnreviewdList() {
        List<Webtoon> Webtoons = webtoonRepository.getUnreviewedList();
        return Webtoons.stream()
            .map(WebtoonResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * ???????????? ?????? ????????? ???????????????.
     * @param keyword ?????????
     * @return ?????? ????????? ?????? dto list
     */
    public List<WebtoonAndGenreResponseDto> getSearchedWebtoon(String keyword) {
        List<WebtoonAndGenreResponseDto> webtoons = webtoonRepository
            .findSearchedWebtoon(keyword.substring(0, 1));
        String trimKeyword = keyword.replace(" ", "");
        List<WebtoonAndGenreResponseDto> webtoonResponseDtos = new ArrayList<>();
        for (WebtoonAndGenreResponseDto webtoon : webtoons) {
            String trimWebtoonTitle = webtoon.getToonTitle().replace(" ", "");
            if (trimWebtoonTitle.contains(trimKeyword)) {
                webtoonResponseDtos.add(webtoon);
            }
        }
        return webtoonResponseDtos;
    }

    public User getUser(String user) {
        return userRepository.findByUserName(user).orElseThrow(
            () -> new IllegalArgumentException("?????? ????????? ???????????? ????????????.")
        );
    }
}
