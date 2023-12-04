package com.darkanddarker.auction.dto.myAuction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DeleteWishListRequestDto {
    private Long auctionItemId;
}
