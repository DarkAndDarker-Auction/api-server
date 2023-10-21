package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AllSearchKeyResponseDto {
    private List<ArmorType> armorTypes;
    private List<HandType> handTypes;
    private List<WeaponType> weaponTypes;
    private List<SlotType> slotTypes;
    private List<Rarity> rarities;
    private List<ItemOption> itemOptions;
    private List<Item> items;
}
