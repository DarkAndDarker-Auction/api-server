package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.search.*;
import com.darkanddarker.auction.service.SearchKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "검색 카테고리 및 옵션 값 API")
@RequestMapping("/search-key-api")
@RequiredArgsConstructor
public class SearchKeyApiController {

    private final SearchKeyService searchService;

    @Operation(summary = "카테고리 검색값 API", description = "모든 검색 카테고리별 값 (ex. 슬롯 타입, 등급) API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/all")
    public ResponseEntity<AllSearchKeyResponseDto> getAllSearchKeys() {
        AllSearchKeyResponseDto allSearchKeyResponseDto = searchService.getAllSearchKeys();
        return ResponseEntity.ok(allSearchKeyResponseDto);
    }

    @Operation(summary = "카테고리 검색값 API - 손 타입", description = "손 타입 API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("hand-type")
    public ResponseEntity<HandTypeResponseDto> getHandTypes() {
        HandTypeResponseDto handTypeResponseDto = searchService.getHandTypes();
        return ResponseEntity.ok(handTypeResponseDto);
    }

    @Operation(summary = "카테고리 검색값 API - 무기 타입", description = "무기 타입 API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/weapon-type")
    public ResponseEntity<WeaponTypeResponseDto> getWeaponTypes() {
        WeaponTypeResponseDto weaponTypes = searchService.getWeaponTypes();
        return ResponseEntity.ok(weaponTypes);
    }

    @Operation(summary = "카테고리 검색값 API - 방어구 타입", description = "방어구 타입 API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/armor-type")
    public ResponseEntity<ArmorTypeResponseDto> getArmorTypes() {
        ArmorTypeResponseDto armorTypeResponseDto = searchService.getArmorTypes();
        return ResponseEntity.ok(armorTypeResponseDto);
    }

    @Operation(summary = "카테고리 검색값 API - 슬롯 타입", description = "슬롯 타입 API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/slot-type")
    public ResponseEntity<SlotTypeResponseDto> getSlotTypes() {
        SlotTypeResponseDto slotTypeResponseDto = searchService.getSlotTypes();
        return ResponseEntity.ok(slotTypeResponseDto);
    }

    @Operation(summary = "카테고리 검색값 API - 등급", description = "등급 API 가져오기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데이터를 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "500", description = "데이터를 가져오는데 실패하였습니다.")
    })
    @GetMapping("/rarities")
    public ResponseEntity<RarityResponseDto> getRarities() {
        RarityResponseDto rarityResponseDto = searchService.getRarities();
        return ResponseEntity.ok(rarityResponseDto);
    }

}
