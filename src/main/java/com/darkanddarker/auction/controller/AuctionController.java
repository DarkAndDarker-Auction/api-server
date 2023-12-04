package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.auction.*;
import com.darkanddarker.auction.dto.search.AuctionItemDetailResponseDto;
import com.darkanddarker.auction.service.auction.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<AuctionItemRegisterResponseDto> registerAuctionItem(@RequestBody AuctionItemRegisterRequestDto auctionItemRegisterRequestDto,
                                                                              @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(auctionService.registerAuctionItem(auctionItemRegisterRequestDto, authorizationHeader));
    }

    @Operation(summary = "경매 물품 삭제 API", description = "사용자 권한 필요. 경매 물품 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 삭제에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "401", description = "권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "삭제에 실패하였습니다.")
    })
    @PostMapping("/delete")
    public void deleteAuctionItem(@RequestBody AuctionItemDeleteRequestDto auctionItemDeleteRequestDto,
                                  @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.deleteAuctionItem(auctionItemDeleteRequestDto, authorizationHeader);
    }

    @Operation(summary = "오퍼 삭제 API", description = "사용자 권한 필요. 오퍼 리스트중 자신이 등록한 오퍼 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "오퍼 삭제에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 오퍼 정보 입니다."),
            @ApiResponse(responseCode = "401", description = "권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "삭제에 실패하였습니다.")
    })
    @PostMapping("/offer/delete")
    public void deleteAuctionItem(@RequestBody DeleteOfferRequestDto deleteOfferRequestDto,
                                  @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.deleteOffer(deleteOfferRequestDto, authorizationHeader);
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

    @Operation(summary = "경매 물품 등록 OFFER 수락 API", description = "판매자가 OFFER 수락시 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OFFER 수락에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 오퍼 정보 이거나 " +
                    "이미 거래중이거나 판매완료된 물품입니다. 새로고침을 통해 정보를 업데이트 하세요."),
            @ApiResponse(responseCode = "500", description = "OFFER 수락에 실패하였습니다.")
    })
    @PostMapping("/offer/confirm")
    public ResponseEntity<Object> getAuctionItemOffers(@RequestBody ConfirmOfferRequestDto confirmOfferRequestDto) {
        auctionService.confirmOffer(confirmOfferRequestDto);
        return ResponseEntity.ok("오퍼 수락에 성공하였습니다.");
    }

    @Operation(summary = "경매 물품 상세정보 GET API", description = "경매 물품 상세 정보 가져오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 상세정보를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "500", description = "물품 상세정보를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/detail")
    public ResponseEntity<AuctionItemDetailResponseDto> getAuctionItemDetails(
            @RequestParam Long auctionItemId,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(auctionService.getAuctionItemDetails(auctionItemId, request.getHeader("Authorization")));
    }

    @Operation(summary = "경매 물품 구매 API", description = "사용자 권한 필요. 경매 물품 구매 및 히스토리 저장. 알림 전송 및 채팅방 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "물품 구매에 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 물품 정보 입니다."),
            @ApiResponse(responseCode = "500", description = "구매에 실패하였습니다.")
    })
    @PostMapping("/buy")
    public ResponseEntity<Object> getAuctionItemOffers(@RequestBody AuctionItemBuyRequestDto auctionItemBuyRequestDto,
                                                       @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.buyAuctionItem(auctionItemBuyRequestDto, authorizationHeader);
        // TODO : 알림 전송 및 채팅방 생성 기능 구현 필요.
        return ResponseEntity.ok("물품 구매에 성공하였습니다.");
    }

    @PostMapping("/complete")
    public ResponseEntity<Object> completeTrade(@RequestBody CompleteTradeRequestDto completeTradeRequestDto,
                                                @RequestHeader("Authorization") String authorizationHeader) {
        auctionService.completeTrade(completeTradeRequestDto, authorizationHeader);
        return ResponseEntity.ok("거래 완료에 성공하였습니다.");
    }
}
