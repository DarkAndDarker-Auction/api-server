package com.darkanddarker.auction.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenRefreshRequestDto {
    private String accessToken;
    private String refreshToken;
}
