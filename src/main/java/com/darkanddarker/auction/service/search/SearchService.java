package com.darkanddarker.auction.service.search;

import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.dto.search.SearchRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.WishListRepository;
import com.darkanddarker.auction.service.myAuction.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final AuctionItemRepository auctionItemRepository;
    private final AuthUtils authUtils;
    private final WishListRepository wishListRepository;

    public AuctionItemsResponseDto findItemsBySearchKey(SearchRequestDto searchRequestDto, Pageable pageable, String authorizationHeader) {
        Specification<AuctionItem> spec = searchRequestDto.buildDynamicQuery();

        Page<AuctionItem> auctionItemsPage = auctionItemRepository.findAll(spec, pageable);
        System.out.println(authorizationHeader);
        if(authorizationHeader != null) {
            Member member = authUtils.extractMemberFromToken(authorizationHeader);
            auctionItemsPage.getContent().forEach((auctionItem) -> {
                auctionItem.setIsInWishList(wishListRepository.existsByMemberAndAuctionItem(member, auctionItem));
            });
        }

        return AuctionItemsResponseDto.builder()
                .auctionItems(auctionItemsPage.getContent())
                .total(auctionItemsPage.getTotalElements())
                .build();
    }
    
}
