package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RarityResponseDto {
    private List<Rarity> rarities;
}
