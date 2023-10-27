package com.darkanddarker.auction.service.auction;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.utils.auction.AuctionItemMapper;
import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.auction.AuctionItemBuyRequestDto;
import com.darkanddarker.auction.dto.auction.AuctionItemOfferRequestDto;
import com.darkanddarker.auction.dto.auction.AuctionItemOfferResponseDto;
import com.darkanddarker.auction.dto.auction.AuctionItemRegisterRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.Offer;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.OfferRepository;
import com.darkanddarker.auction.repository.auction.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionService {

    private final AuctionItemMapper auctionItemMapper;
    private final AuctionItemRepository auctionItemRepository;
    private final AuthUtils authUtils;
    private final OfferRepository offerRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    @Transactional
    public void registerAuctionItem(AuctionItemRegisterRequestDto auctionItemRegisterRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemMapper.registerRequestToAuctionItemMapper(auctionItemRegisterRequestDto, member);
        auctionItemRepository.save(auctionItem);
    }

    @Transactional
    public void offerAuctionItem(AuctionItemOfferRequestDto auctionItemOfferRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemOfferRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));

        auctionItemRepository.save(auctionItem.updateStatus(AuctionStatusType.HAS_OFFER));

        offerRepository.save(Offer.builder()
                .auctionItem(auctionItem)
                .member(member)
                .priceGold(auctionItemOfferRequestDto.getItemPriceSet().getPriceGold())
                .priceGoldenKey(auctionItemOfferRequestDto.getItemPriceSet().getPriceGoldenKey())
                .priceGoldIngot(auctionItemOfferRequestDto.getItemPriceSet().getPriceGoldIngot())
                .priceEventCurrency(auctionItemOfferRequestDto.getItemPriceSet().getPriceEventCurrency())
                .build());
    }

    public AuctionItemOfferResponseDto getAuctionItemOffers(Long auctionItemId) {
        List<Offer> offers = offerRepository.findByAuctionItemIdOrderByOfferedAtDesc(auctionItemId);

        return new AuctionItemOfferResponseDto(offers);
    }

    @Transactional
    public void buyAuctionItem(AuctionItemBuyRequestDto auctionItemBuyRequestDto,
                                                 String authorizationHeader) {
        Member buyer = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemBuyRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));
        auctionItemRepository.save(auctionItem.updateStatus(AuctionStatusType.TRADING));

        tradeHistoryRepository.save(TradeHistory.builder()
                .auctionItem(auctionItem)
                .buyer(buyer)
                .seller(auctionItem.getSeller())
                .build());
    }
}
