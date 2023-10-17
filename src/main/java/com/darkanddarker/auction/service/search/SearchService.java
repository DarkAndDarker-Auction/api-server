package com.darkanddarker.auction.service.search;

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

    private final AuctionItemSpecification auctionItemSpecification;
    private final AuctionItemRepository auctionItemRepository;

    public List<AuctionItem> findItemsBySearchKey(SearchKeyCollection searchKeys) {
        Specification<AuctionItem> spec = searchKeys.buildDynamicQuery();
        return auctionItemRepository.findAll(spec);
    }

}