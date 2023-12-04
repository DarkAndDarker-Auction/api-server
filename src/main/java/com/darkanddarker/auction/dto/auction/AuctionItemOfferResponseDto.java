package com.darkanddarker.auction.dto.auction;

import com.darkanddarker.auction.model.auction.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AuctionItemOfferResponseDto {
    private List<Offer> offers;

    public AuctionItemOfferResponseDto(List<Offer> offers) {
        this.offers = offers;
    }
}
