package com.darkanddarker.auction.service.specification;

import com.darkanddarker.auction.model.auction.AuctionItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AuctionItemSpecification {
    public Specification<AuctionItem> SearchKeyOptionGreaterThan(SearchKeySpec searchKeySpec) {
        return (Root<AuctionItem> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.greaterThanOrEqualTo(root.get(searchKeySpec.getOptionName()), searchKeySpec.getOptionValue());
    }
}
