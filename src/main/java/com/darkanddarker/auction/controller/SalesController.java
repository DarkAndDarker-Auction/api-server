package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.myAuction.AuctionItemsResponseDto;
import com.darkanddarker.auction.dto.myAuction.TradeHistoriesResponseDto;
import com.darkanddarker.auction.service.myAuction.SalesService;
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
@RequestMapping("/sales")
@Tag(name = "물품 판매 현황 API")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @Operation(summary = "물품 판매 현황 카테고리-전체 API", description = "현재까지 등록한 물품 내역 리스트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 물품 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/total")
    public ResponseEntity<AuctionItemsResponseDto> getSalesTotal(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok(salesService.getSalesTotal(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 판매 현황 카테고리-판매중 API", description = "현재까지 등록한 물품 중 판매중인 내역")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 물품 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/on-sale")
    public ResponseEntity<AuctionItemsResponseDto> getSalesOnSale(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(salesService.getSalesOnSale(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 판매 현황 카테고리-거래중 API", description = "현재까지 등록한 물품 중 거래중인 내역")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 물품 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/trading")
    public ResponseEntity<AuctionItemsResponseDto> getSalesTrading(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(salesService.getSalesTrading(authorizationHeader, pageable));
    }

    @Operation(summary = "물품 판매 현황 카테고리-판매완료 API", description = "현재까지 등록한 물품 중 판매완료된 내역")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 물품 내역을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 없거나 잘못된 사용자 입니다.")
    })
    @GetMapping("/sold-out")
    public ResponseEntity<AuctionItemsResponseDto> getSalesSoldOut(
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(salesService.getSalesSoldOut(authorizationHeader, pageable));
    }

}
