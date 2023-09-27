package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.SignupRequestDto;
import com.darkanddarker.auction.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입", description = "사용자 회원 등록. 이메일 인증 및 닉네임 중복 검증 절차 후 가능.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입되어 있는 유저입니다.")
    })
    @PostMapping("/signup")
    public void signup(SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
    }
}
