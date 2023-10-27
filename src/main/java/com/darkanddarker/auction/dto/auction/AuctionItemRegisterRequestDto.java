package com.darkanddarker.auction.dto.auction;

import com.darkanddarker.auction.model.member.Member;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuctionItemRegisterRequestDto {

    @NotNull
    private Long itemId;
    @NotNull
    private Long rarityId;

    @JsonUnwrapped
    private ItemOptionSet itemOptions;

    private ItemPriceSet itemPriceSet;

}


