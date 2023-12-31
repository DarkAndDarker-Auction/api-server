package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.HandType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HandTypeResponseDto {
    private List<HandType> handTypes;
}
