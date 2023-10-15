package com.darkanddarker.auction.service;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.dto.auth.NicknameVerificationRequestDto;
import com.darkanddarker.auction.dto.auth.SignupRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberVerificationServiceTest {

    @Autowired
    private MemberVerificationService memberVerificationService;
    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        memberService.signup(SignupRequestDto.builder()
                .email("test01@test.com")
                .password("test01")
                .nickname("test01")
                .build());
    }

    @Test
    public void nicknameVerification_duplicate() {
        // given
        NicknameVerificationRequestDto given = NicknameVerificationRequestDto.builder()
                .nickname("test01")
                .build();

        // when, then
        Assertions.assertThatThrownBy(() -> memberVerificationService.nicknameVerification(given))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("이미 존재하는 닉네임 입니다.");
    }

    @Test
    public void nicknameVerification_success() {
        // given
        NicknameVerificationRequestDto given = NicknameVerificationRequestDto.builder()
                .nickname("test02")
                .build();

        // when, then
        Assertions.assertThatCode(() -> memberVerificationService.nicknameVerification(given))
                .doesNotThrowAnyException();
    }

}