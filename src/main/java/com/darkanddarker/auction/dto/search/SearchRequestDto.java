package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.searchKey.Item;
import com.darkanddarker.auction.service.specification.OptionSearchKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.darkanddarker.auction.common.constant.Constant.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    private final List<OptionSearchKey> optionSearchKeys = new ArrayList<>();
    private String nameSearchKey;
    private Long raritySearchKey;
    private Long slotTypeSearchKey;
    private List<AuctionStatusType> auctionStatusTypes;

    public Specification<AuctionItem> buildDynamicQuery() {
        Specification<AuctionItem> spec = optionSearchKeys.stream()
                .map(this::addOptionSearchKeySpecAnd)
                .reduce(Specification.where(null), Specification::and);

        if (this.nameSearchKey != null) {
            spec = Specification.where(spec).and(addNameSearchKeySpecAnd());
        }

        if (this.raritySearchKey != null) {
            spec = Specification.where(spec).and(addRaritySearchKeySpecAnd());
        }

        if (this.slotTypeSearchKey != null) {
            spec = Specification.where(spec).and(addCategorySearchKeySpecAnd(SEARCH_KEY_SLOT_TYPE, this.slotTypeSearchKey));
        }

        spec = Specification.where(spec).and(filterByStatus()); // filter only [activate, has_offer] status item
        spec = Specification.where(spec).and(filterExpiredItems());

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
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<AuctionItem, Item> itemJoin = root.join("item");
            predicates.add(builder.equal(itemJoin.get(SEARCH_KEY_NAME), this.nameSearchKey));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<AuctionItem> addRaritySearchKeySpecAnd() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get(SEARCH_KEY_RARITY), this.raritySearchKey));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<AuctionItem> addCategorySearchKeySpecAnd(String categoryName, Long value) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (categoryName == null || value == null) {
                throw new BadRequestException("잘못된 요청입니다. 값을 확인해주세요.");
            }
            Join<AuctionItem, Item> itemJoin = root.join("item");
            predicates.add(builder.equal(itemJoin.get(categoryName), value));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<AuctionItem> filterByStatus() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get("auctionStatusType"), AuctionStatusType.ACTIVE));
            predicates.add(builder.equal(root.get("auctionStatusType"), AuctionStatusType.HAS_OFFER));
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<AuctionItem> filterExpiredItems() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.greaterThan(root.get("expirationTime"), LocalDateTime.now()));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
