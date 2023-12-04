package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.auction.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AuctionItemDetailResponseDto {
    private AuctionItem auctionItem;
    private String sellerNickname;
}
