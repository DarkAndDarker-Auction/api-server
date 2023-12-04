package com.darkanddarker.auction.service.myAuction;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.myAuction.AddWishListRequestDto;
import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.dto.myAuction.BatchUpdateWishListRequestDto;
import com.darkanddarker.auction.dto.myAuction.DeleteWishListRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.WishList;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishListService {

    private final WishListRepository wishListRepository;
    private final AuctionItemRepository auctionItemRepository;
    private final AuthUtils authUtils;

    public AuctionItemsResponseDto getWishList(String authorizationHeader, Pageable pageable) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);

        Page<WishList> wishLists = wishListRepository
                .findByMemberAndAuctionItemExpirationTimeAfter(member, LocalDateTime.now(), pageable);

        List<AuctionItem> auctionItems = wishLists.stream().map(WishList::getAuctionItem).collect(Collectors.toList());
        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItems)
                .total(wishLists.getTotalElements())
                .build();
    }

    @Transactional
    public void batchUpdateWishList(BatchUpdateWishListRequestDto batchUpdateWishListRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        batchUpdateWishListRequestDto.getWishListChanged().forEach((wishListChanged) -> {
            AuctionItem auctionItem = auctionItemRepository.findById(wishListChanged.getAuctionItemId())
                    .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));
            if (wishListChanged.isInWishList()) {
                wishListRepository.save(WishList.builder()
                        .auctionItem(auctionItem)
                        .member(member)
                        .build());
            } else {
                WishList wishList = wishListRepository.findByMemberAndAuctionItem(member, auctionItem);
                wishListRepository.delete(wishList);
            }
        });
    }

    @Transactional
    public void addWishList(AddWishListRequestDto addWishListRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(addWishListRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));

        wishListValidate(member, auctionItem);

        if (wishListRepository.existsByMemberAndAuctionItem(member, auctionItem))
            throw new BadRequestException("이미 위시리스트에 등록된 물품입니다.");

        wishListRepository.save(WishList.builder()
                .auctionItem(auctionItem)
                .member(member)
                .build());
    }

    @Transactional
    public void deleteWishList(DeleteWishListRequestDto deleteWishListRequestDto, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        AuctionItem auctionItem = auctionItemRepository.findById(deleteWishListRequestDto.getAuctionItemId())
                .orElseThrow(() -> new BadRequestException("잘못된 물품 정보 입니다."));

        wishListValidate(member, auctionItem);

        WishList wishList = wishListRepository.findByMemberAndAuctionItem(member, auctionItem);

        if (!member.equals(wishList.getMember())) {
            throw new BadRequestException("권한이 없습니다.");
        }

        wishListRepository.delete(wishList);
    }

    public void wishListValidate(Member member, AuctionItem auctionItem) {
        if (member.equals(auctionItem.getSeller())) {
            throw new BadRequestException("자신이 등록한 물품은 위시리스트에 등록할 수 없습니다.");
        }
    }
}
