package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.model.searchKey.ItemOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemOptionResponseDto {
    private List<ItemOption> itemOptions;

}
