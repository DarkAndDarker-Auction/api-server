package com.darkanddarker.auction.service;

import com.darkanddarker.auction.common.jwt.TokenBlacklist;
import com.darkanddarker.auction.common.jwt.TokenProvider;
import com.darkanddarker.auction.dto.auth.SigninRequestDto;
import com.darkanddarker.auction.dto.auth.SignoutRequestDto;
import com.darkanddarker.auction.dto.auth.SignupRequestDto;
import com.darkanddarker.auction.dto.auth.TokenDto;
import com.darkanddarker.auction.repository.auth.RefreshTokenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private TokenBlacklist tokenBlacklist;

    private static final String EMAIL = "test001@gmail.com";
    private static final String PASSWORD = "test001";
    private static final String NICKNAME = "test001";

    @BeforeEach
    public void setUser() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .build();
        memberService.signup(signupRequestDto);
    }

    @DisplayName("로그인 성공 테스트")
    @Test
    public void signinSuccessTest() {
        // given
        final String SIGNIN_EMAIL = "test001@gmail.com";
        final String SIGNIN_PASSWORD = "test001";
        SigninRequestDto signinRequestDto = SigninRequestDto.builder()
                .email(SIGNIN_EMAIL)
                .password(SIGNIN_PASSWORD)
                .build();

        // when
        TokenDto token = authService.signin(signinRequestDto);
        String accessToken = token.getAccessToken();
        String refreshToken = token.getRefreshToken();

        // then
        tokenProvider.validateToken(accessToken);
        tokenProvider.validateToken(refreshToken);
    }

    @DisplayName("로그인 실패 테스트")
    @Test
    public void signinFailureTest() {
        // given
        final String SIGNIN_EMAIL = "test001@gmail.com";
        final String SIGNIN_PASSWORD = "failureTest001"; // test001 << correct password
        SigninRequestDto signinRequestDto = SigninRequestDto.builder()
                .email(SIGNIN_EMAIL)
                .password(SIGNIN_PASSWORD)
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> authService.signin(signinRequestDto))
                .isInstanceOf(BadCredentialsException.class);

    }

    @DisplayName("로그아웃 테스트")
    @Test
    public void signoutTest() {
        // given
        TokenDto tokenDto = signinSuccess();
        String accessToken = tokenDto.getAccessToken();
        SignoutRequestDto signoutRequestDto = SignoutRequestDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        // when
        authService.signout(signoutRequestDto);

        // then
        String email = tokenProvider.getAuthentication(accessToken).getName();
        Assertions.assertThat(refreshTokenRepository.findByEmail(email).isEmpty()).isTrue(); // 리프레시토큰 정보 삭제
        Assertions.assertThat(tokenBlacklist.isAccessTokenBlacklisted(accessToken)).isTrue(); // 액세스토큰 블랙리스트 등록

    }

    private TokenDto signinSuccess() {
        final String SIGNIN_EMAIL = "test001@gmail.com";
        final String SIGNIN_PASSWORD = "test001";
        SigninRequestDto signinRequestDto = SigninRequestDto.builder()
                .email(SIGNIN_EMAIL)
                .password(SIGNIN_PASSWORD)
                .build();

        return authService.signin(signinRequestDto);
    }
}