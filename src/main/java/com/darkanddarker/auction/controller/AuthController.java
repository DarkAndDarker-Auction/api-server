package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.EmailVerificationRequestDto;
import com.darkanddarker.auction.dto.SendVerificationCodeRequestDto;
import com.darkanddarker.auction.dto.SignupRequestDto;
import com.darkanddarker.auction.service.EmailVerificationService;
import com.darkanddarker.auction.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final EmailVerificationService emailVerificationService;

    @Operation(summary = "회원 가입", description = "사용자 회원 등록. 이메일 인증 및 닉네임 중복 검증 절차 후 가능.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입되어 있는 유저입니다.")
    })
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.noContent().build();
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
            @ApiResponse(responseCode = "401", description = "올바르지 않은 인증번호 입니다.")
    })
    @PostMapping("/email-verification/verify")
    public ResponseEntity<Object> verifyEmailVerificationCode(@RequestBody EmailVerificationRequestDto emailVerificationRequestDto) {
        emailVerificationService.verify(emailVerificationRequestDto);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

}
