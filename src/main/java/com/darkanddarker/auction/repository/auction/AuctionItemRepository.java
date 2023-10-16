package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long>, JpaSpecificationExecutor<AuctionItem> {

}
