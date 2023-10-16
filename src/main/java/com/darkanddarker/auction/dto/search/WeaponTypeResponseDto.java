package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WeaponTypeResponseDto {
    private List<WeaponType> weaponTypes;
}
