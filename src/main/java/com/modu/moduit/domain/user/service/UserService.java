package com.modu.moduit.domain.user.service;

import com.modu.moduit.global.security.jwt.TokenResponseDto;

public interface UserService {

    TokenResponseDto processingGoogleUser(GoogleUserDto userDto);
}
