package com.darkanddarker.auction.service.auth;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.exception.UnauthorizedException;
import com.darkanddarker.auction.common.jwt.TokenBlacklist;
import com.darkanddarker.auction.dto.auth.SignoutRequestDto;
import com.darkanddarker.auction.dto.auth.TokenDto;
import com.darkanddarker.auction.common.jwt.TokenProvider;
import com.darkanddarker.auction.dto.auth.SigninRequestDto;
import com.darkanddarker.auction.model.jwt.RefreshToken;
import com.darkanddarker.auction.repository.auth.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final TokenBlacklist tokenBlacklist;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenDto signin(SigninRequestDto signinRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = signinRequestDto.toAuthentication();

        if (authenticationToken == null) {
            throw new BadRequestException("사용자 정보가 올바르지 않습니다.");
        }

        return getToken(authenticationToken);
    }

    @Transactional
    public TokenDto getToken(UsernamePasswordAuthenticationToken authenticationToken){
        // 입력값(email, password)을 통해 얻은 사용자 인증 정보로 토큰 발급.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .email(authentication.getName())
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public void signout(SignoutRequestDto signoutRequestDto){
        String accessToken = signoutRequestDto.getAccessToken();
        String email = tokenProvider.getAuthentication(accessToken).getName();

        tokenBlacklist.blacklistAccessToken(accessToken);

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByEmail(email);

        if(refreshToken.isEmpty()) {
            throw new UnauthorizedException("이미 로그아웃 되었거나 인가되지 않은 사용자 입니다.");
        }
        refreshTokenRepository.deleteByEmail(email);
    }

}
