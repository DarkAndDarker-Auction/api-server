package com.darkanddarker.auction.dto.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuctionItemRegisterResponseDto {
    private AuctionItem auctionItem;
}
