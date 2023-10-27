package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArmorTypeResponseDto {
    private List<ArmorType> armorTypes;
}
