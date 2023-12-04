package com.darkanddarker.auction.model.auction;

import com.darkanddarker.auction.model.BaseEntity;
import com.darkanddarker.auction.model.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class TradeHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member seller;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member buyer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AuctionItem auctionItem;

    private Long priceGold;
    private Long priceGoldIngot;
    private Long priceGoldenKey;
    private Long priceEventCurrency;

}
