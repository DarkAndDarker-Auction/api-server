package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.myAuction.AddWishListRequestDto;
import com.darkanddarker.auction.dto.myAuction.BatchUpdateWishListRequestDto;
import com.darkanddarker.auction.dto.myAuction.DeleteWishListRequestDto;
import com.darkanddarker.auction.service.myAuction.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "위시리스트 API")
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @Operation(summary = "위시리스트 등록 API", description = "위시리스트에 경매 물품 등록.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "위시리스트 등록에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "권한이 없거나 없는 물품정보입니다."),
            @ApiResponse(responseCode = "500", description = "위시리스트 등록에 실패하였습니다.")
    })
    @PostMapping("/add")
    public ResponseEntity<Object> addWishList(@RequestBody AddWishListRequestDto addWishListRequestDto,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        wishListService.addWishList(addWishListRequestDto, authorizationHeader);
        return ResponseEntity.ok("위시리스트 등록에 성공하였습니다.");
    }

    @Operation(summary = "위시리스트 삭제 API", description = "위시리스트에 경매 물품 삭제.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "위시리스트 삭제에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "권한이 없거나 없는 물품정보입니다."),
            @ApiResponse(responseCode = "500", description = "위시리스트 삭제에 실패하였습니다.")
    })
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteWishList(@RequestBody DeleteWishListRequestDto deleteWishListRequestDto,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        wishListService.deleteWishList(deleteWishListRequestDto, authorizationHeader);
        return ResponseEntity.ok("위시리스트 삭제에 성공하였습니다.");
    }

    @Operation(summary = "위시리스트 묶음 수정 API", description = "검색 페이지에서 위시리스트 등록시 묶음 수정.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "위시리스트 묶음 수정에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "권한이 없거나 없는 물품정보입니다."),
            @ApiResponse(responseCode = "500", description = "위시리스트 묶음 수정에 실패하였습니다.")
    })
    @PostMapping("/batch-update")
    public ResponseEntity<Object> batchUpdateWishList(@RequestBody BatchUpdateWishListRequestDto batchUpdateWishListRequestDto,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        wishListService.batchUpdateWishList(batchUpdateWishListRequestDto, authorizationHeader);
        return ResponseEntity.ok("위시리스트 수정에 성공하였습니다.");
    }

    @Operation(summary = "위시리스트 목록 API", description = "위시리스트 목록 가져오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "위시리스트 목록을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다."),
            @ApiResponse(responseCode = "500", description = "위시리스트 목록을 가져오는데 실패하였습니다.")
    })
    @GetMapping("")
    public ResponseEntity<Object> addWishList(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return ResponseEntity.ok(wishListService.getWishList(authorizationHeader, pageable));
    }
}
