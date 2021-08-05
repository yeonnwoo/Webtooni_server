package com.webtooni.webtooniverse.domain.user.service;

import com.webtooni.webtooniverse.domain.user.domain.User;
import com.webtooni.webtooniverse.domain.user.domain.UserRepository;
import com.webtooni.webtooniverse.domain.user.dto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    private String getEncodedPassword(String password) {
        return ("{noop}" + password);
    }

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUserName();
        String password = requestDto.getPassword();
        String passwordChecker = requestDto.getPasswordChecker();
        String userImg = requestDto.getUserImg();
        String userEmail = requestDto.getUserEmail();

        Optional<User> found = userRepository.findByUserName(username);
        if (username.equals("") || password.equals("") || passwordChecker.equals("")) {
            throw new IllegalArgumentException("username || password || passwordChecker가 비어있습니다.");
//        } else if (password.length() < 4) {
//            throw new IllegalArgumentException("password는 최소 4글자입니다.");
        } else if (!password.equals(passwordChecker)) {
            throw new IllegalArgumentException("password와 passwordChecker가 다릅니다.");
        } else if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        password = getEncodedPassword(requestDto.getPassword());
        User user = new User(username, userEmail , password, userImg);
        userRepository.save(user);
    }
}
