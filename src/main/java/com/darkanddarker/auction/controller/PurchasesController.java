package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.service.myAuction.PurchasesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
@Tag(name = "물품 구매 현황 API")
@RequiredArgsConstructor
public class PurchasesController {

    private final PurchasesService purchasesService;
    @Operation(summary = "물품 구매 현황 카테고리-전체 API", description = "현재까지 구매한 혹은 구매중인 물품 내역 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 구매 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/total")
    public ResponseEntity<AuctionItemsResponseDto> getPurchasesTotal(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(purchasesService.getPurchasesTotal(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 구매 현황 카테고리-거래중 API", description = "현재 구매중인 물품 내역 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 구매 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/trading")
    public ResponseEntity<AuctionItemsResponseDto> getPurchasesTrading(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(purchasesService.getPurchasesTrading(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 구매 현황 카테고리-오퍼 API", description = "현재 오퍼한 물품 내역 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 오퍼 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/offered")
    public ResponseEntity<AuctionItemsResponseDto> getPurchasesOffered(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(purchasesService.getPurchasesOffered(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 구매 현황 카테고리-거래완료 API", description = "구매완료된 물품 내역 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 구매완료 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/completed")
    public ResponseEntity<AuctionItemsResponseDto> getPurchasesCompleted(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(purchasesService.getPurchasesCompleted(authorizationHeader, pageable));
    }
}
