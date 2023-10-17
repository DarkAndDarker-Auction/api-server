package com.darkanddarker.auction.service.specification;

import com.darkanddarker.auction.model.auction.AuctionItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class AuctionItemSpecification {
    public Specification<AuctionItem> SearchKeyOptionGreaterThan(SearchKey searchKeySpec) {
        return (Root<AuctionItem> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.greaterThanOrEqualTo(root.get(searchKeySpec.getOptionName()), searchKeySpec.getOptionValue());
    }
}
