package com.darkanddarker.auction.model.auction;

import com.darkanddarker.auction.model.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    AuctionItem auctionItem;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    Member member;

    LocalDateTime offeredAt;

    @PrePersist
    private void setDefault() {
        this.offeredAt = LocalDateTime.now();
    }

    private Long priceGold;
    private Long priceGoldIngot;
    private Long priceGoldenKey;
    private Long priceEventCurrency;
}
