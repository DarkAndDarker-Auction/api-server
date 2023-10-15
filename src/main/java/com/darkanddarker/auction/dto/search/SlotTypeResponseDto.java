package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SlotTypeResponseDto {
    private List<SlotType> slotTypes;
}
