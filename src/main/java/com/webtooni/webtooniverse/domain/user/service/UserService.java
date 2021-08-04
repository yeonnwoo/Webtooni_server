package com.webtooni.webtooniverse.domain.user.service;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //베스트 리뷰어 가져오기
    public List<BestReviewerResponseDto> getBestReviewerRank(){
        List<User>  bestReviewer = userRepository.getBestReviewer();
        return bestReviewer
                .stream()
                .map(BestReviewerResponseDto::new)
                .collect(Collectors.toList());
    }
}
