package com.darkanddarker.auction.service.specification;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.model.auction.AuctionItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SearchKeyCollection {
    private final List<SearchKey> searchKeys = new ArrayList<>();

    public List<SearchKey> getSearchKeys() {
        return searchKeys;
    }

    public Specification<AuctionItem> buildDynamicQuery() {
        return searchKeys.stream()
                .map(this::addSearchKeySpecAnd)
                .reduce(Specification.where(null), Specification::and);
    }

    private Specification<AuctionItem> addSearchKeySpecAnd(SearchKey searchKey) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchKey.getOptionName() == null || searchKey.getOptionValue() == null) {
                throw new BadRequestException("잘못된 요청입니다. 값을 확인해주세요.");
            }
            predicates.add(builder.equal(root.get(searchKey.getOptionName()), searchKey.getOptionValue()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
