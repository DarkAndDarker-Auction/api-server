package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.auction.AuctionItemBuyRequestDto;
import com.darkanddarker.auction.dto.auction.AuctionItemOfferRequestDto;
import com.darkanddarker.auction.dto.auction.AuctionItemOfferResponseDto;
import com.darkanddarker.auction.dto.auction.AuctionItemRegisterRequestDto;
import com.darkanddarker.auction.service.auction.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "경매 API")
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "경매 물품 등록 API", description = "사용자 권한 필요. 경매 물품 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 등록에 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "등록에 실패하였습니다.")
    })
    @PostMapping("/register")
    public void registerAuctionItem(@RequestBody AuctionItemRegisterRequestDto auctionItemRegisterRequestDto,
                                    @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.registerAuctionItem(auctionItemRegisterRequestDto, authorizationHeader);
    }

    @Operation(summary = "경매 물품 OFFER API", description = "사용자 권한 필요. 등록된 경매 물품 가격 제시")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 가격 제시에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "500", description = "등록에 실패하였습니다.")
    })
    @PostMapping("/offer")
    public void offerAuctionItem(@RequestBody AuctionItemOfferRequestDto auctionItemOfferRequestDto,
                                 @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.offerAuctionItem(auctionItemOfferRequestDto, authorizationHeader);
    }

    @Operation(summary = "경매 물품 등록 OFFER 리스트 GET API", description = "경매 물품 현재까지 OFFER LIST 가져오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OFFER 리스트를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "500", description = "OFFER 리스트를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/offer")
    public ResponseEntity<AuctionItemOfferResponseDto> getAuctionItemOffers(@RequestParam Long auctionItemId) {
        return ResponseEntity.ok(auctionService.getAuctionItemOffers(auctionItemId));
    }

    @Operation(summary = "경매 물품 구매 API", description = "사용자 권한 필요. 경매 물품 구매 및 히스토리 저장. 알림 전송 및 채팅방 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 구매에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "500", description = "구매에 실패하였습니다.")
    })
    @GetMapping("/buy")
    public ResponseEntity<Object> getAuctionItemOffers(@RequestBody AuctionItemBuyRequestDto auctionItemBuyRequestDto,
                                                       @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.buyAuctionItem(auctionItemBuyRequestDto, authorizationHeader);
        // TODO : 알림 전송 및 채팅방 생성 기능 구현 필요.
        return ResponseEntity.ok("물품 구매에 성공하였습니다.");
    }

}
