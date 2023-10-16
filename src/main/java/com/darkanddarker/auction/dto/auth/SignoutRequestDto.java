package com.darkanddarker.auction.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class SignoutRequestDto {
    private String accessToken;
    private String refreshToken;
}
