package com.darkanddarker.auction.dto.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.Offer;
import com.darkanddarker.auction.model.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuctionItemOfferRequestDto {
    ItemPriceSet itemPriceSet;
    Long auctionItemId;

}
