package com.darkanddarker.auction.service.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionSearchKey {
    private String optionName;
    private Long optionValue;
}
