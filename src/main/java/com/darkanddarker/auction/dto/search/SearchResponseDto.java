package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponseDto {
    private List<AuctionItem> auctionItems;

}
