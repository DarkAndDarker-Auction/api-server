package com.darkanddarker.auction.common.utils.auth;

import com.darkanddarker.auction.common.exception.UnauthorizedException;
import com.darkanddarker.auction.common.jwt.TokenProvider;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public Member extractMemberFromToken(String authorizationHeader) {
        if (authorizationHeader.isEmpty()) {
            throw new UnauthorizedException("토큰 정보가 없습니다.");
        }
        String email = tokenProvider.getEmailFromAccessToken(authorizationHeader.replace("Bearer ", ""));
        return memberRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("없는 사용자 입니다."));
    }

}
