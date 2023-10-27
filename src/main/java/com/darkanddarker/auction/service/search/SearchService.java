package com.darkanddarker.auction.service.search;

import com.darkanddarker.auction.dto.search.SearchRequestDto;
import com.darkanddarker.auction.dto.search.SearchResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.service.specification.AuctionItemSpecification;
import com.darkanddarker.auction.service.specification.SearchKeyCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final AuctionItemRepository auctionItemRepository;

    public SearchResponseDto findItemsBySearchKey(SearchRequestDto searchRequestDto) {
        Specification<AuctionItem> spec = searchRequestDto.buildDynamicQuery();
        return new SearchResponseDto(auctionItemRepository.findAll(spec));
    }

}
