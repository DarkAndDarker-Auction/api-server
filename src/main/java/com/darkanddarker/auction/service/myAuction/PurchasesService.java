package com.darkanddarker.auction.service.myAuction;

import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.Offer;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.OfferRepository;
import com.darkanddarker.auction.repository.auction.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchasesService {

    private final TradeHistoryRepository tradeHistoryRepository;
    private final AuthUtils authUtils;
    private final OfferRepository offerRepository;

    public AuctionItemsResponseDto getPurchasesTotal(String authorizationHeader, Pageable pageable) {
        Member buyer = authUtils.extractMemberFromToken(authorizationHeader);

        Page<TradeHistory> tradeHistories = tradeHistoryRepository.findByBuyerAndAuctionItemExpirationTimeAfter(buyer, LocalDateTime.now(), pageable);
        List<AuctionItem> auctionItems = tradeHistories.stream().map(TradeHistory::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(tradeHistories.getTotalElements())
                .build();
    }

    public AuctionItemsResponseDto getPurchasesTrading(String authorizationHeader, Pageable pageable) {
        Member buyer = authUtils.extractMemberFromToken(authorizationHeader);

        Page<TradeHistory> tradeHistories = tradeHistoryRepository.findByBuyerAndAuctionItemAuctionStatusType(buyer, AuctionStatusType.TRADING, pageable);
        List<AuctionItem> auctionItems = tradeHistories.stream().map(TradeHistory::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(tradeHistories.getTotalElements())
                .build();
    }

    public AuctionItemsResponseDto getPurchasesOffered(String authorizationHeader, Pageable pageable) {
        Member buyer = authUtils.extractMemberFromToken(authorizationHeader);

        Page<Offer> offers = offerRepository.findLatestOffersByMemberAndAuctionItemExpirationTimeAfter(buyer, LocalDateTime.now(), pageable);
        List<AuctionItem> auctionItems = offers.getContent().stream().map(Offer::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(offers.getTotalElements())
                .build();
    }

    public AuctionItemsResponseDto getPurchasesCompleted(String authorizationHeader, Pageable pageable) {
        Member buyer = authUtils.extractMemberFromToken(authorizationHeader);

        Page<TradeHistory> tradeHistories = tradeHistoryRepository.findByBuyerAndAuctionItemAuctionStatusType(buyer, AuctionStatusType.COMPLETED, pageable);
        List<AuctionItem> auctionItems = tradeHistories.stream().map(TradeHistory::getAuctionItem).collect(Collectors.toList());

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(tradeHistories.getTotalElements())
                .build();
    }
}
