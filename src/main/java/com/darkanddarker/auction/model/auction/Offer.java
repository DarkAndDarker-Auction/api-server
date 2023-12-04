package com.darkanddarker.auction.model.auction;

import com.darkanddarker.auction.model.BaseEntity;
import com.darkanddarker.auction.model.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Offer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    Member member;

    @JsonProperty("memberId")
    public Long getMemberId() {
        return member.getId();
    }

    LocalDateTime offeredAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "auction_item_id")
    @JsonIgnore
    private AuctionItem auctionItem;

    @PrePersist
    private void setDefault() {
        this.offeredAt = LocalDateTime.now();
    }

    private Long priceGold;
    private Long priceGoldIngot;
    private Long priceGoldenKey;
    private Long priceEventCurrency;

    public void setAuctionItem(AuctionItem auctionItem) {
        this.auctionItem = auctionItem;
    }

}
