package com.webtoon.demo.domain.user.service;

import com.webtoon.demo.domain.genre.domain.Genre;
import com.webtoon.demo.domain.user.domain.User;
import com.webtoon.demo.domain.user.domain.UserGenre;
import com.webtoon.demo.domain.user.domain.UserGenreRepository;
import com.webtoon.demo.domain.user.domain.UserRepository;
import com.webtoon.demo.domain.user.dto.UserGenreRequestDto;
import com.webtoon.demo.domain.user.dto.UserInfoRequestDto;
import com.webtoon.demo.domain.user.security.kakao.KakaoOAuth2;
import com.webtoon.demo.domain.user.security.kakao.KakaoUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    private final KakaoOAuth2 kakaoOAuth2;
    private final UserGenreRepository userGenreRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, KakaoOAuth2 kakaoOAuth2, AuthenticationManager authenticationManager, UserGenreRepository userGenreRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kakaoOAuth2 = kakaoOAuth2;
        this.authenticationManager = authenticationManager;
        this.userGenreRepository = userGenreRepository;
    }

    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);

            kakaoUser = new User(encodedPassword, kakaoId);
            userRepository.save(kakaoUser);
        }

        // 로그인 처리
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(kakaoId, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @Transactional
    public void updateInfo(Long id, UserInfoRequestDto requestDto){
        User user = userRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 회원이 존재하지 않습니다.")
        );
        user.update(requestDto);
    }

    @Transactional
    public List<UserGenre> pickGenre(User user, UserGenreRequestDto requestDto){
        List<UserGenre> userGenres = new ArrayList<>();
        List<Genre> genres = requestDto.getGenres();
        for (Genre genre : genres){
            UserGenre userGenre = new UserGenre(user, genre);
            userGenreRepository.save(userGenre);
            userGenres.add(userGenre);
        }
        return userGenres;
    }
}
