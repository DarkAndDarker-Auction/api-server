package com.darkanddarker.auction.service;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.dto.auth.NicknameVerificationRequestDto;
import com.darkanddarker.auction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberVerificationService {

    private final MemberRepository memberRepository;

    public void nicknameVerification(NicknameVerificationRequestDto nicknameVerificationRequestDto) {
        if (memberRepository.existsByNickname(nicknameVerificationRequestDto.getNickname())) {
            throw new BadRequestException("이미 존재하는 닉네임 입니다.");
        }
    }

}
