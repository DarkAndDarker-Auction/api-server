package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.WishList;
import com.darkanddarker.auction.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Page<WishList> findByMemberAndAuctionItemExpirationTimeAfter(Member member, LocalDateTime curTime, Pageable pageable);
    WishList findByMemberAndAuctionItem(Member member, AuctionItem auctionItem);
    boolean existsByMemberAndAuctionItem(Member member, AuctionItem auctionItem);
}
