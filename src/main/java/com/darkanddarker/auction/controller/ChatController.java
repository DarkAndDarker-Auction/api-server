package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.chat.ChatMessageResponseDto;
import com.darkanddarker.auction.dto.chat.ChatSendRequestDto;
import com.darkanddarker.auction.dto.chat.TradingAuctionItemsChatRoomResponseDto;
import com.darkanddarker.auction.service.chat.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "채팅 API")
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/send")
    public void sendChatMessage(ChatSendRequestDto chatSendRequestDto,
                                SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        chatService.sendMessage(chatSendRequestDto);
    }

    @GetMapping("")
    public ResponseEntity<ChatMessageResponseDto> getChatMessage(
            @RequestParam Long chatRoomId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return ResponseEntity.ok(chatService.getChatMessages(chatRoomId, authorizationHeader));
    }

    @GetMapping("/room")
    public ResponseEntity<List<TradingAuctionItemsChatRoomResponseDto>> getChatRooms(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return ResponseEntity.ok(chatService.getChatRooms(authorizationHeader));
    }

}
