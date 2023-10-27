package com.darkanddarker.auction.repository.auction;

import com.darkanddarker.auction.model.auction.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {

}
