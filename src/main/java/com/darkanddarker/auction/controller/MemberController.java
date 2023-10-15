package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.auth.NicknameVerificationRequestDto;
import com.darkanddarker.auction.service.MemberVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "회원 생성 및 관리 API")
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberVerificationService memberVerificationService;

    @Operation(summary = "닉네임 중복 확인", description = "회원 가입시 닉네임 중복 확인 API.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능한 닉네임 입니다."),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 닉네임 입니다.")
    })
    @PostMapping("/verification/nickname")
    public ResponseEntity<Object> verifyNickname(@RequestBody NicknameVerificationRequestDto nicknameVerificationRequestDto) {
        memberVerificationService.nicknameVerification(nicknameVerificationRequestDto);
        return ResponseEntity.ok("사용 가능한 닉네임 입니다.");
    }

}
