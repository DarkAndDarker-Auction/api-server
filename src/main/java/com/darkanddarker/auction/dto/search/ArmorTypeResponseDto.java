package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ArmorTypeResponseDto {
    private List<ArmorType> armorTypes;
}
