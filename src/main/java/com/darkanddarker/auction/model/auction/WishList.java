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
public class WishList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private AuctionItem auctionItem;
}
