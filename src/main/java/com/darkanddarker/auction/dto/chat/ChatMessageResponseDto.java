package com.darkanddarker.auction.dto.chat;

import com.darkanddarker.auction.model.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatMessageResponseDto {
    private List<ChatMessage> chatMessages;
}
