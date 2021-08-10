package com.webtooni.webtooniverse.domain.user.service;

import com.webtooni.webtooniverse.domain.genre.domain.Genre;
import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserGenre;
import com.webtooni.webtooniverse.domain.user.domain.UserGenreRepository;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.UserGenreRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.UserInfoRequestDto;
import com.webtooni.webtooniverse.domain.user.dto.response.BestReviewerResponseDto;
import com.webtooni.webtooniverse.domain.user.dto.response.UserInfoResponseDto;
import com.webtooni.webtooniverse.domain.user.security.JwtTokenProvider;
import com.webtooni.webtooniverse.domain.user.security.kakao.KakaoOAuth2;
import com.webtooni.webtooniverse.domain.user.security.kakao.KakaoUserInfo;
import com.webtooni.webtooniverse.domain.webtoon.domain.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final WebtoonRepository webtoonRepository;
    private final KakaoOAuth2 kakaoOAuth2;
    private final UserGenreRepository userGenreRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public String kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String kakao = String.valueOf(kakaoId);
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakao(kakao)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);

            kakaoUser = new User(encodedPassword, kakao);
            userRepository.save(kakaoUser);
        }

        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(kakao, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return jwtTokenProvider.createToken(kakaoUser.getKakao(), kakaoUser.getId(), kakaoUser.getUserName(), kakaoUser.getUserGrade(), kakaoUser.getUserImg());
    }


    @Transactional
    public void updateInfo(Long id, UserInfoRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 회원이 존재하지 않습니다.")
        );
        user.update(requestDto);
    }

    /**
     * TODO 수정 필요(오류)
     */

    //베스트 리뷰어 가져오기
    public List<BestReviewerResponseDto> getBestReviewerRank(){
        return webtoonRepository.findBestReviewerForMain();
    }

    @Transactional
    public List<UserGenre> pickGenre(User user, UserGenreRequestDto requestDto) {
        List<UserGenre> userGenres = new ArrayList<>();
        List<Genre> genres = requestDto.getGenres();
        for (Genre genre : genres) {
            UserGenre userGenre = new UserGenre(user, genre);
            userGenreRepository.save(userGenre);
            userGenres.add(userGenre);
        }
        return userGenres;
    }

    public UserInfoResponseDto getUserInfo(User user) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저를 찾지 못하였습니다.")
        );
        return new UserInfoResponseDto(findUser);

    }
}

