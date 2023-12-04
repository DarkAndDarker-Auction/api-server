package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.auth.*;
import com.darkanddarker.auction.service.auth.AuthService;
import com.darkanddarker.auction.service.auth.CustomUserDetailService;
import com.darkanddarker.auction.service.auth.EmailVerificationService;
import com.darkanddarker.auction.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@Tag(name = "인증 API")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @Operation(summary = "회원 가입", description = "사용자 회원 등록. 이메일 인증 및 닉네임 중복 검증 절차 후 가능.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입되어 있는 유저입니다.")
    })
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok("회원 가입에 성공하였습니다.");
    }

    @Operation(summary = "로그인", description = "이메일, 비밀번호를 통한 사용자 로그인.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "사용자 정보가 올바르지 않습니다.")
    })
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody SigninRequestDto signinRequestDto) {
        return ResponseEntity.ok(authService.signin(signinRequestDto));
    }

    @Operation(summary = "로그아웃", description = "로그인된 사용자 로그아웃.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃에 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "이미 로그아웃 되었거나 인가되지 않은 사용자 입니다.")
    })
    @PostMapping("/signout")
    public ResponseEntity<Object> signout(@RequestBody SignoutRequestDto signoutRequestDto) {
        authService.signout(signoutRequestDto);
        return ResponseEntity.ok("로그아웃에 성공하였습니다.");
    }

    @Operation(summary = "토큰 재발급", description = "액세스 토큰만료 시 리프레시 토큰으로 재발급.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재발급에 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "이미 로그아웃 되었거나 인가되지 않은 사용자 입니다.")
    })
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRefreshRequestDto tokenRefreshRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRefreshRequestDto));
    }

    @Operation(summary = "이메일 인증번호 전송", description = "회원가입 단계 중 이메일 인증단계. 요청된 이메일 중복검사 진행 후 인증번호 발송.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호 발송에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입된 이메일 주소 입니다.")
    })
    @PostMapping("/email-verification/send")
    public ResponseEntity<Object> sendEmailVerificationCode(@RequestBody SendVerificationCodeRequestDto sendVerificationCodeRequestDto)
            throws MessagingException, IOException {
        emailVerificationService.verifyEmailDuplication(sendVerificationCodeRequestDto);
        emailVerificationService.sendVerificationCode(sendVerificationCodeRequestDto);
        return ResponseEntity.ok("인증번호 발송에 성공하였습니다.");
    }

    @Operation(summary = "이메일 인증번호 검증", description = "이메일 인증번호 일치 여부 확인.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 인증번호 입니다.")
    })
    @PostMapping("/email-verification/verify")
    public ResponseEntity<Object> verifyEmailVerificationCode(@RequestBody EmailVerificationRequestDto emailVerificationRequestDto) {
        emailVerificationService.verify(emailVerificationRequestDto);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

}
