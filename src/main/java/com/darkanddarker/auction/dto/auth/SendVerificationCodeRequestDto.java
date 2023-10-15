package com.darkanddarker.auction.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class SendVerificationCodeRequestDto {

    private String email;
}
