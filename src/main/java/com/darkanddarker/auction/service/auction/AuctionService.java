package com.darkanddarker.auction.service.auction;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.exception.NotFoundException;
import com.darkanddarker.auction.common.exception.UnauthorizedException;
import com.darkanddarker.auction.common.utils.auction.AuctionItemMapper;
import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.auction.*;
import com.darkanddarker.auction.dto.search.AuctionItemDetailResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.Offer;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.chat.ChatRoom;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.OfferRepository;
import com.darkanddarker.auction.repository.auction.TradeHistoryRepository;
import com.darkanddarker.auction.repository.auction.WishListRepository;
import com.darkanddarker.auction.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
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
    private final WishListRepository wishListRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public AuctionItemRegisterResponseDto registerAuctionItem(AuctionItemRegisterRequestDto auctionItemRegisterRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemMapper.registerRequestToAuctionItemMapper(auctionItemRegisterRequestDto, member);
        AuctionItem registeredAuctionItem = auctionItemRepository.save(auctionItem);
        return AuctionItemRegisterResponseDto.builder()
                .auctionItem(registeredAuctionItem)
                .build();
    }

    @Transactional
    public void offerAuctionItem(AuctionItemOfferRequestDto auctionItemOfferRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemOfferRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));

        buyAuctionItemValidate(auctionItem, member);

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

    public AuctionItemDetailResponseDto getAuctionItemDetails(Long auctionItemId, String authorizationHeader) {
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemId)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 물품 정보입니다."));

        if (authorizationHeader != null) {
            Member member = authUtils.extractMemberFromToken(authorizationHeader);
            auctionItem.setIsInWishList(wishListRepository.existsByMemberAndAuctionItem(member, auctionItem));
        }

        String sellerNickName = auctionItem.getSeller().getNickname();
        return AuctionItemDetailResponseDto.builder()
                .auctionItem(auctionItem)
                .sellerNickname(sellerNickName)
                .build();

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

        buyAuctionItemValidate(auctionItem, buyer);

        auctionItemRepository.save(auctionItem.updateStatus(AuctionStatusType.TRADING));
        chatRoomRepository.save(ChatRoom.builder()
                .auctionItem(auctionItem)
                .buyer(buyer)
                .seller(auctionItem.getSeller())
                .build());
        tradeHistoryRepository.save(TradeHistory.builder()
                .auctionItem(auctionItem)
                .buyer(buyer)
                .seller(auctionItem.getSeller())
                .priceGold(auctionItemBuyRequestDto.getItemPriceSet().getPriceGold())
                .priceEventCurrency(auctionItemBuyRequestDto.getItemPriceSet().getPriceEventCurrency())
                .priceGoldIngot(auctionItemBuyRequestDto.getItemPriceSet().getPriceGoldIngot())
                .priceGoldenKey(auctionItemBuyRequestDto.getItemPriceSet().getPriceGoldenKey())
                .build());
    }

    @Transactional
    public void confirmOffer(ConfirmOfferRequestDto confirmOfferRequestDto) {
        Offer offer = offerRepository.findById(confirmOfferRequestDto.getOfferId())
                .orElseThrow(() -> new BadRequestException("잘못된 오퍼 정보 입니다."));
        AuctionItem auctionItem = offer.getAuctionItem();

        if (!auctionItem.getAuctionStatusType().equals(AuctionStatusType.HAS_OFFER)) {
            throw new BadRequestException("이미 거래중이거나 판매완료된 물품입니다. 새로고침을 통해 정보를 업데이트 하세요.");

        }

        auctionItemRepository.save(auctionItem.confirmOffer(offer));
        chatRoomRepository.save(ChatRoom.builder()
                .auctionItem(auctionItem)
                .buyer(offer.getMember())
                .seller(auctionItem.getSeller())
                .build());
        tradeHistoryRepository.save(
                TradeHistory.builder()
                        .auctionItem(auctionItem)
                        .seller(auctionItem.getSeller())
                        .buyer(offer.getMember())
                        .priceGold(offer.getPriceGold())
                        .priceGoldenKey(offer.getPriceGoldenKey())
                        .priceGoldIngot(offer.getPriceGoldIngot())
                        .priceEventCurrency(offer.getPriceEventCurrency())
                        .build());
    }

    @Transactional
    public void completeTrade(CompleteTradeRequestDto completeTradeRequestDto, String authorizationHeader) {
        AuctionItem auctionItem = auctionItemRepository.findById(completeTradeRequestDto.getAuctionItemId())
                .orElseThrow(() -> new NotFoundException("없는 물품 정보입니다. auctionItemId : " + completeTradeRequestDto.getAuctionItemId()));
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        TradeHistory tradeHistory = tradeHistoryRepository.findByAuctionItem(auctionItem);

        if (!member.equals(tradeHistory.getBuyer())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        auctionItem.updateStatus(AuctionStatusType.COMPLETED);
        auctionItemRepository.save(auctionItem);
    }

    @Transactional
    public void deleteAuctionItem(AuctionItemDeleteRequestDto auctionItemDeleteRequestDto,
                                  String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemDeleteRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));
        hasAuthority(member, auctionItem);
        auctionItemRepository.delete(auctionItem);
    }

    @Transactional
    public void deleteOffer(DeleteOfferRequestDto deleteOfferRequestDto, String authorizationHeader) {
        Offer offer = offerRepository.findById(deleteOfferRequestDto.getOfferId())
                .orElseThrow(() -> new BadRequestException("없는 오퍼 정보 입니다."));
        Member member = authUtils.extractMemberFromToken(authorizationHeader);

        if (!member.equals(offer.getMember())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        offerRepository.delete(offer);
    }

    private void hasAuthority(Member member, AuctionItem auctionItem) {
        if (!member.equals(auctionItem.getSeller())) {
            throw new UnauthorizedException("권한이 없습니다.");
        }
    }

    private void buyAuctionItemValidate(AuctionItem auctionItem, Member buyer) {
        if (auctionItem.getAuctionStatusType().equals(AuctionStatusType.COMPLETED) ||
                auctionItem.getAuctionStatusType().equals(AuctionStatusType.TRADING)) {
            throw new BadRequestException("이미 판매완료된 물품입니다.");
        }

        if (auctionItem.getSeller().equals(buyer)) {
            throw new BadRequestException("자신이 판매중인 아이템은 구매할 수 없습니다.");
        }
    }
}
