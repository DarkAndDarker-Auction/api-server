package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.auction.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuctionItemsResponseDto {
    private List<AuctionItem> auctionItems;
    private Long total;
    private int pageNumber;

}
