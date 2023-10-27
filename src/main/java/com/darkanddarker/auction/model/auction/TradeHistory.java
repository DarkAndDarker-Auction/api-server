package com.darkanddarker.auction.model.auction;

import com.darkanddarker.auction.model.member.Member;
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
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member seller;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member buyer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AuctionItem auctionItem;

}
