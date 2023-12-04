package com.darkanddarker.auction.dto.myAuction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddWishListRequestDto {
    private Long auctionItemId;

}
