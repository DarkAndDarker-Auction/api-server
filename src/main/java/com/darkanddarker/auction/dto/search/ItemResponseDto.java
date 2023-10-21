package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemResponseDto {
    private List<Item> items;
}
