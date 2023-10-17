package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.auction.AuctionItemRegisterRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.service.auction.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "경매 API")
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "경매 물품 등록 API", description = "사용자 권한 필요. 경매 물품 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입에 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "등록에 실패하였습니다.")
    })
    @PostMapping("/register")
    public void registerAuctionItem(@RequestBody AuctionItemRegisterRequestDto auctionItemRegisterRequestDto) {
        auctionService.registerAuctionItem(auctionItemRegisterRequestDto);
    }

}
