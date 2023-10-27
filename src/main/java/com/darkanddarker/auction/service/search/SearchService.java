package com.darkanddarker.auction.service.search;

import com.darkanddarker.auction.dto.search.SearchRequestDto;
import com.darkanddarker.auction.dto.search.SearchResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.service.specification.AuctionItemSpecification;
import com.darkanddarker.auction.service.specification.SearchKeyCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        Pageable pageable = PageRequest.of(searchRequestDto.getPageNumber(), searchRequestDto.getPageSize());
        Page<AuctionItem> auctionItemsPage = auctionItemRepository.findAll(spec, pageable);

        return new SearchResponseDto(auctionItemsPage.getContent(), auctionItemsPage.getTotalElements(), searchRequestDto.getPageNumber());
    }

}
