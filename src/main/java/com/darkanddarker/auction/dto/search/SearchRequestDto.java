package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.searchKey.Item;
import com.darkanddarker.auction.service.specification.OptionSearchKey;
import com.darkanddarker.auction.service.specification.SearchKeyCollection;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    private final List<OptionSearchKey> optionSearchKeys = new ArrayList<>();
    private String nameSearchKey;

    public Specification<AuctionItem> buildDynamicQuery() {
        Specification<AuctionItem> spec =  optionSearchKeys.stream()
                .map(this::addOptionSearchKeySpecAnd)
                .reduce(Specification.where(null), Specification::and);

        if (!this.nameSearchKey.isEmpty()) {
            spec.and(addNameSearchKeySpecAnd());
        }

        return spec;
    }

    private Specification<AuctionItem> addOptionSearchKeySpecAnd(OptionSearchKey searchKey) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchKey.getOptionName() == null || searchKey.getOptionValue() == null) {
                throw new BadRequestException("잘못된 요청입니다. 값을 확인해주세요.");
            }
            predicates.add(builder.greaterThanOrEqualTo(root.get(searchKey.getOptionName()), searchKey.getOptionValue()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<AuctionItem> addNameSearchKeySpecAnd() {
        final String SEARCH_KEY_NAME = "name";

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<AuctionItem, Item> itemJoin = root.join("item");
            predicates.add(builder.equal(itemJoin.get(SEARCH_KEY_NAME), this.nameSearchKey));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
