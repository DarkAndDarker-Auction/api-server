package com.darkanddarker.auction.dto.myAuction;

import com.darkanddarker.auction.model.auction.TradeHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TradeHistoriesResponseDto {
    private List<TradeHistory> tradeHistories;
    private Long total;
}
