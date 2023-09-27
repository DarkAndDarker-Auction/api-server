package com.darkanddarker.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameVerificationRequestDto {
    private String nickname;

    @Builder
    public NicknameVerificationRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
