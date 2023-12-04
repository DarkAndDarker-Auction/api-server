package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long>, JpaSpecificationExecutor<AuctionItem> {
    Page<AuctionItem> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "offers")
    Optional<AuctionItem> findById(Long auctionItemId);

    Page<AuctionItem> findBySellerAndExpirationTimeAfterAndAuctionStatusTypeIn(
            Member member, LocalDateTime curTime, List<AuctionStatusType> auctionStatusTypes, Pageable pageable);

}
