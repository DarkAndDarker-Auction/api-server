package com.darkanddarker.auction.dto.search;

import com.darkanddarker.auction.service.specification.SearchKeyCollection;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchRequestDto {
    @JsonUnwrapped
    private SearchKeyCollection searchKey;
}
