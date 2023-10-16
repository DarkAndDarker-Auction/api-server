package com.darkanddarker.auction.service;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.service.specification.AuctionItemSpecification;
import com.darkanddarker.auction.service.specification.SearchKeySpecCollection;
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

    public List<AuctionItem> findItemsBySearchKey(SearchKeySpecCollection searchKeys) {
        Specification<AuctionItem> spec = Specification.where(
                auctionItemSpecification.SearchKeyOptionGreaterThan()
        ).and(
                auctionItemSpecification.SearchKeyOptionGreaterThan(armorRatingValue)
        );

        return yourEntityRepository.findAll(spec);
    }

}
