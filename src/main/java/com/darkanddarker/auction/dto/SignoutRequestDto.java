package com.darkanddarker.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignoutRequestDto {
    String accessToken;
    String refreshToken;
}
