package com.darkanddarker.auction.service.specification;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.model.auction.AuctionItem;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SearchKeySpecCollection {
    private final List<SearchKeySpec> searchKeySpecs = new ArrayList<>();

    public void addSearchKeySpec(SearchKeySpec searchKeySpec) {
        searchKeySpecs.add(searchKeySpec);
    }

    public void removeSearchKeySpec(SearchKeySpec searchKeySpec) {
        searchKeySpecs.remove(searchKeySpec);
    }

    public List<SearchKeySpec> getSearchKeySpecs() {
        return searchKeySpecs;
    }

    public Specification<AuctionItem> buildDynamicQuery() {
        return searchKeySpecs.stream()
                .map(this::addSearchKeySpecAnd)
                .reduce(Specification.where(null), Specification::and);
    }

    private Specification<AuctionItem> addSearchKeySpecAnd(SearchKeySpec searchKeySpec) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchKeySpec.getOptionName() == null || searchKeySpec.getOptionValue() == null) {
                throw new BadRequestException("잘못된 요청입니다. 값을 확인해주세요.");
            }
            predicates.add(builder.equal(root.get(searchKeySpec.getOptionName()), searchKeySpec.getOptionValue()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
