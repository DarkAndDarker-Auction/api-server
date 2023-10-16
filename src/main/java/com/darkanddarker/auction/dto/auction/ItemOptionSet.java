package com.darkanddarker.auction.dto.auction;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ItemOptionSet {
    private Map<String, Long> itemOptions;
}
