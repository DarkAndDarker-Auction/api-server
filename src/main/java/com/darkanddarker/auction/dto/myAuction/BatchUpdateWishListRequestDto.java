package com.darkanddarker.auction.dto.myAuction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BatchUpdateWishListRequestDto {
    private List<WishListChangedDto> wishListChanged;

}
