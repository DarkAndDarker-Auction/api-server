package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.search.SearchRequestDto;
import com.darkanddarker.auction.dto.search.SearchResponseDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.service.search.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "물품 검색 API")
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "아이템 검색 - 옵션", description = "옵션 이름 및 수치별 물품 검색." +
            "옵션 별 수치는 항상 같거나 큰(>=) 값 으로 검색." +
            "복수의 옵션 검색 가능.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 값을 확인해주세요."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @PostMapping("auction-item")
    public SearchResponseDto getAuctionItemsBySearchKey(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.findItemsBySearchKey(searchRequestDto);
    }

}
