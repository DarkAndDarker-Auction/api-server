package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.Offer;
import com.darkanddarker.auction.model.member.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByAuctionItemIdOrderByOfferedAtDesc(Long auctionItemId);

    @Query("SELECT o FROM Offer o " +
            "WHERE o.member = :member AND o.id IN (" +
            "    SELECT MAX(oi.id) FROM Offer oi " +
            "    WHERE oi.member = :member AND oi.auctionItem.expirationTime > :curTime " +
            "    GROUP BY oi.auctionItem)" +
            "ORDER BY o.offeredAt DESC")
    Page<Offer> findLatestOffersByMemberAndAuctionItemExpirationTimeAfter(
            @Param("member") Member member,
            @Param("curTime") LocalDateTime curTime, Pageable pageable);

}
