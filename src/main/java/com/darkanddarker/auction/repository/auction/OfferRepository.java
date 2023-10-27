package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByAuctionItemIdOrderByOfferedAtDesc(Long auctionItemId);

}
