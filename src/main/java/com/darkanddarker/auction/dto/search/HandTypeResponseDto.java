package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.HandType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class HandTypeResponseDto {
    private List<HandType> handTypes;

    public HandTypeResponseDto(List<HandType> handTypes) {
        this.handTypes = handTypes;
    }

    public HandTypeResponseDto toDto(List<HandType> handTypes) {
        this.handTypes = handTypes;
        return this;
    }
}
