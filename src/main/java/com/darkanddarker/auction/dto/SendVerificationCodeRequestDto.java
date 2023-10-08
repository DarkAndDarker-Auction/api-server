package com.darkanddarker.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SendVerificationCodeRequestDto {

    private String email;
}
