package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
    Page<TradeHistory> findBySeller(Member member, Pageable pageable);
    Page<TradeHistory> findByBuyerAndAuctionItemExpirationTimeAfter(Member member, LocalDateTime curTime, Pageable pageable);
    Page<TradeHistory> findByBuyerAndAuctionItemAuctionStatusType(Member member, AuctionStatusType auctionStatusType, Pageable pageable);
    Page<TradeHistory> findBySellerAndAuctionItemAuctionStatusType(Member member, AuctionStatusType auctionStatusType, Pageable pageable);
    @Query("SELECT t FROM TradeHistory t " +
            "WHERE (t.seller = :member OR t.buyer = :member) ")
    List<TradeHistory> findByMember(@Param("member") Member member);


    TradeHistory findByAuctionItem(AuctionItem auctionItem);
}
