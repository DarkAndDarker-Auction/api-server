package com.darkanddarker.auction.dto.myAuction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuctionItemsResponseDto {
    private List<AuctionItem> auctionItems;
    private Long total;
}
