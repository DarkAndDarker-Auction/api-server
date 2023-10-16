package com.darkanddarker.auction.service.auction;

import com.darkanddarker.auction.common.utils.auction.AuctionItemMapper;
import com.darkanddarker.auction.dto.auction.AuctionItemRegisterRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionService {

//    private final AuctionItemRepository auctionItemRepository;
    private final AuctionItemMapper auctionItemMapper;
    private final EntityManager entityManager;
    private final AuctionItemRepository auctionItemRepository;

    @Transactional
    public void registerAuctionItem(AuctionItemRegisterRequestDto auctionItemRegisterRequestDto) {
        AuctionItem auctionItem = auctionItemMapper.registerRequestToAuctionItemMapper(auctionItemRegisterRequestDto);
        auctionItemRepository.save(auctionItem);
    }
}
