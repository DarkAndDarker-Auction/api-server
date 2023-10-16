package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RarityResponseDto {
    private List<Rarity> rarities;
}
