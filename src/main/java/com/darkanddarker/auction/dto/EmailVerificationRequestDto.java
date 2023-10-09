package com.darkanddarker.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class EmailVerificationRequestDto {

    private String email;
    private String code;
}
