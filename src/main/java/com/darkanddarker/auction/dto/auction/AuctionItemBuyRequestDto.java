package com.darkanddarker.auction.dto.auction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuctionItemBuyRequestDto {
    private Long auctionItemId;
    private ItemPriceSet itemPriceSet;
}
