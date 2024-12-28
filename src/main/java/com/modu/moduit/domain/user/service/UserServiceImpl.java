package com.modu.moduit.domain.user.service;

import com.modu.moduit.domain.user.model.Provider;
import com.modu.moduit.domain.user.model.Role;
import com.modu.moduit.domain.user.model.Users;
import com.modu.moduit.domain.user.repository.UserRepository;
import com.modu.moduit.global.config.ClientConfig;
import com.modu.moduit.global.security.jwt.JwtTokenProvider;
import com.modu.moduit.global.security.jwt.TokenResponseDto;
import java.util.Collections;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenResponseDto processingGoogleUser(GoogleUserDto userDto) {
        Optional<Users> optionalUser = userRepository.findByEmail(userDto.getEmail());

        Users user = optionalUser.orElseGet(() -> createNewGoogleUser(userDto));
        return generateTokenResponse(user.getId());
    }

    private Users createNewGoogleUser(GoogleUserDto userDto) {
        Users newUser = Users.builder()
            .email(userDto.getEmail())
            .profileImgUrl(userDto.getProfileImgUrl())
            .providers(Collections.singletonList(Provider.GOOGLE))
            .subjectIds(Collections.singletonList(userDto.getSubjectId()))
            .role(Role.USER)
            .build();

        return userRepository.save(newUser);
    }

    private TokenResponseDto generateTokenResponse(String userId) {
        String accessToken = jwtTokenProvider.createAccessToken(userId, ClientConfig.APP);
        String refreshToken = jwtTokenProvider.createRefreshToken(userId, ClientConfig.APP);

        return TokenResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
