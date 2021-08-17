package com.webtooni.webtooniverse.domain.user.security;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String kakao) throws UsernameNotFoundException {
    long kakaoId = Long.parseLong(kakao);
    User user = userRepository.findByKakaoId(kakaoId)
        .orElseThrow(() -> new UsernameNotFoundException("Can't find " + kakaoId));

    return new UserDetailsImpl(user);
  }
}
