package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.service.specification.SearchKeySpec;
import com.darkanddarker.auction.service.specification.SearchKeySpecCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchRequestDto {
    private SearchKeySpecCollection searchKeySpecs;
}
