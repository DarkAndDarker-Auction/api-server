package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.WeaponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeaponTypeResponseDto {
    private List<WeaponType> weaponTypes;
}
