package com.webtooni.webtooniverse.domain.webtoon.dto.response;

import com.webtooni.webtooniverse.domain.webtoon.domain.Webtoon;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankTotalResponseDto {

    private Webtoon webtoons;
    private Set<String> genres;
    private Float weeklyAvgPoint;

    public RankTotalResponseDto(Webtoon webtoons,
        Set<String> genres, List<Float> userPoint) {
        this.webtoons = webtoons;
        this.genres = genres;
        this.weeklyAvgPoint = getWeeklyAvgPoint(userPoint);
    }

    public float getWeeklyAvgPoint(List<Float> userPointList) {
        float sum = 0;
        int count = 0;
        for (Float userPoint : userPointList) {
            sum += userPoint;
            count += 1;
        }
        return sum / count;
    }
}
