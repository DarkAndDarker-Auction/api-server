package com.darkanddarker.auction.dto.auction;

import com.darkanddarker.auction.common.utils.auction.AuctionItemMapper;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.searchKey.Item;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.util.annotation.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuctionItemRegisterRequestDto {

    @NotNull
    private Long itemId;
    @NotNull
    private Long rarityId;

    @JsonUnwrapped
    private ItemOptionSet itemOptions;
    private ItemPriceSet itemPriceSet;

}


