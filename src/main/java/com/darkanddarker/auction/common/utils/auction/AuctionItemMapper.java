package com.darkanddarker.auction.common.utils.auction;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.dto.auction.AuctionItemRegisterRequestDto;
import com.darkanddarker.auction.dto.auction.ItemPriceSet;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.searchKey.Item;
import com.darkanddarker.auction.model.searchKey.Rarity;
import com.darkanddarker.auction.repository.searchKey.ItemRepository;
import com.darkanddarker.auction.repository.searchKey.RarityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuctionItemMapper {

    private final EntityManager entityManager;
    private final RarityRepository rarityRepository;
    private final ItemRepository itemRepository;

    public AuctionItem auctionItemRegisterMapper(AuctionItemRegisterRequestDto auctionItemRegisterRequestDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuctionItem> query = criteriaBuilder.createQuery(AuctionItem.class);
        Root<AuctionItem> root = query.from(AuctionItem.class);

        Join<AuctionItem, Item> itemJoin = root.join("item", JoinType.INNER);
        Join<AuctionItem, Rarity> rarityJoin = root.join("rarity", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(itemJoin.get("id"), auctionItemRegisterRequestDto.getItemId()));
        predicates.add(criteriaBuilder.equal(rarityJoin.get("id"), auctionItemRegisterRequestDto.getRarityId()));

        // Map itemOptions dynamically
        Map<String, Long> itemOptions = auctionItemRegisterRequestDto.getItemOptions().getItemOptions();
        itemOptions.entrySet().stream()
                .map(entry -> {
                    String optionName = entry.getKey();
                    Long optionValue = entry.getValue();
                    return criteriaBuilder.equal(root.get(optionName), optionValue);
                })
                .forEach(predicates::add);

        return entityManager.createQuery(query.select(root).where(predicates.toArray(new Predicate[0]))).getSingleResult();
    }

    public AuctionItem registerRequestToAuctionItemMapper(AuctionItemRegisterRequestDto auctionItemRegisterRequestDto) {

        Item item = itemRepository.findById(auctionItemRegisterRequestDto.getItemId()).orElseThrow(() -> new BadRequestException("잘못된 아이템 정보입니다."));
        Rarity rarity = rarityRepository.findById(auctionItemRegisterRequestDto.getRarityId()).orElseThrow(() -> new BadRequestException("잘못된 등급 정보입니다."));
        ItemPriceSet itemPrice = auctionItemRegisterRequestDto.getItemPriceSet();

        AuctionItem auctionItem = AuctionItem.builder()
                .item(item)
                .rarity(rarity)
                .priceGold(itemPrice.getPriceGold())
                .priceGoldenKey(itemPrice.getPriceGoldenKey())
                .priceGoldIngot(itemPrice.getPriceGoldIngot())
                .priceEventCurrency(itemPrice.getPriceEventCurrency())
                .build();

        Map<String, Long> itemOptions = auctionItemRegisterRequestDto.getItemOptions().getItemOptions();
        for (Map.Entry<String, Long> entry : itemOptions.entrySet()) {
            String optionName = entry.getKey();
            Long optionValue = entry.getValue();

            try {
                Field field = AuctionItem.class.getDeclaredField(optionName);
                field.setAccessible(true);
                field.set(auctionItem, optionValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new BadRequestException("잘못된 옵션 정보입니다.");
            }
        }

        return auctionItem;
    }
}
