package com.darkanddarker.auction.service.chat;

import com.darkanddarker.auction.common.exception.NotFoundException;
import com.darkanddarker.auction.common.exception.UnauthorizedException;
import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.chat.ChatMessageResponseDto;
import com.darkanddarker.auction.dto.chat.ChatSendRequestDto;
import com.darkanddarker.auction.dto.chat.TradingAuctionItemsChatRoomResponseDto;
import com.darkanddarker.auction.model.auction.AuctionStatusType;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.chat.ChatMessage;
import com.darkanddarker.auction.model.chat.ChatRoom;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.auction.TradeHistoryRepository;
import com.darkanddarker.auction.repository.chat.ChatMessageRepository;
import com.darkanddarker.auction.repository.chat.ChatRoomRepository;
import com.darkanddarker.auction.repository.member.MemberRepository;
import com.darkanddarker.auction.repository.searchKey.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final AuthUtils authUtils;
    private final TradeHistoryRepository tradeHistoryRepository;

    @Transactional
    public void sendMessage(ChatSendRequestDto chatSendRequestDto) {
        persistChatMessage(chatSendRequestDto);
        chatSendRequestDto.setCreatedAt(LocalDateTime.now());
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatSendRequestDto.getChatRoomId(), chatSendRequestDto);
    }

    public ChatMessageResponseDto getChatMessages(Long chatRoomId, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 채팅방입니다."));

        if (!chatRoom.getSeller().equals(member) && !chatRoom.getBuyer().equals(member)) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        return ChatMessageResponseDto.builder()
                .chatMessages(chatMessageRepository.findByChatRoom(chatRoom))
                .build();
    }

    public List<TradingAuctionItemsChatRoomResponseDto> getChatRooms(String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        List<TradeHistory> tradingAuctionItems = tradeHistoryRepository.findByMember(member);

        return tradingAuctionItems.stream().map((t) -> {
            ChatRoom chatRoom = chatRoomRepository.findByAuctionItem(t.getAuctionItem());
            ChatMessage latestChat = chatMessageRepository.findFirstByChatRoomOrderByCreatedAtDesc(chatRoom);

            return TradingAuctionItemsChatRoomResponseDto.builder()
                    .auctionItem(t.getAuctionItem())
                    .chatRoom(chatRoom)
                    .tradeHistory(t)
                    .latestChat(latestChat)
                    .build();
        }).collect(Collectors.toList());
    }

    private void persistChatMessage(ChatSendRequestDto requestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(requestDto.getChatRoomId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 채팅방입니다."));
        Member member = memberRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new NotFoundException("잘못된 사용자 정보입니다."));

        chatMessageRepository.save(ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(member)
                .message(requestDto.getMessage())
                .build());
    }

}
