package com.darkanddarker.auction.service.myAuction;

import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.dto.myAuction.TradeHistoriesResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesService {

    private final AuthUtils authUtils;
    private final AuctionItemRepository auctionItemRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public AuctionItemsResponseDto getSalesTotal(String authorizationHeader, Pageable pageable) {
        List<AuctionStatusType> onSaleStatusTypes = totalStatusTypes();
        return getSalesAuctionItems(authorizationHeader, pageable, onSaleStatusTypes);
    }

    public AuctionItemsResponseDto getSalesOnSale(String authorizationHeader, Pageable pageable) {
        List<AuctionStatusType> onSaleStatusTypes = onSaleStatusTypes();
        return getSalesAuctionItems(authorizationHeader, pageable, onSaleStatusTypes);
    }

    public AuctionItemsResponseDto getSalesTrading(String authorizationHeader, Pageable pageable) {
        Member seller = authUtils.extractMemberFromToken(authorizationHeader);
        Page<TradeHistory> tradeHistories = tradeHistoryRepository
                .findBySellerAndAuctionItemAuctionStatusType(seller, AuctionStatusType.TRADING, pageable);
        List<AuctionItem> auctionItems = tradeHistories.getContent().stream()
                .map(TradeHistory::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(tradeHistories.getTotalElements())
                .build();
    }

    public AuctionItemsResponseDto getSalesSoldOut(String authorizationHeader, Pageable pageable) {
        Member seller = authUtils.extractMemberFromToken(authorizationHeader);
        Page<TradeHistory> tradeHistories = tradeHistoryRepository
                .findBySellerAndAuctionItemAuctionStatusType(seller, AuctionStatusType.COMPLETED, pageable);
        List<AuctionItem> auctionItems = tradeHistories.getContent().stream()
                .map(TradeHistory::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(tradeHistories.getTotalElements())
                .build();
    }

    public TradeHistoriesResponseDto getSellHistory(String authorizationHeader, Pageable pageable) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);

        Page<TradeHistory> tradeHistories = tradeHistoryRepository.findBySeller(member, pageable);
        return TradeHistoriesResponseDto.builder()
                .tradeHistories(tradeHistories.getContent())
                .total(tradeHistories.getTotalElements())
                .build();
    }

    private AuctionItemsResponseDto getSalesAuctionItems(String authorizationHeader, Pageable pageable, List<AuctionStatusType> statusTypes) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);

        Page<AuctionItem> auctionItems = auctionItemRepository.
                findBySellerAndExpirationTimeAfterAndAuctionStatusTypeIn(member, LocalDateTime.now(), statusTypes, pageable);

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems.getContent())
                .total(auctionItems.getTotalElements())
                .build();
    }

    private List<AuctionStatusType> totalStatusTypes() {
        return Arrays.asList(AuctionStatusType.ACTIVE, AuctionStatusType.HAS_OFFER, AuctionStatusType.TRADING, AuctionStatusType.COMPLETED);
    }

    private List<AuctionStatusType> onSaleStatusTypes() {
        return Arrays.asList(AuctionStatusType.ACTIVE, AuctionStatusType.HAS_OFFER);
    }

    private List<AuctionStatusType> tradingStatusTypes() {
        return Collections.singletonList(AuctionStatusType.TRADING);
    }

    private List<AuctionStatusType> soldOutStatusTypes() {
        return Collections.singletonList(AuctionStatusType.COMPLETED);
    }

}
