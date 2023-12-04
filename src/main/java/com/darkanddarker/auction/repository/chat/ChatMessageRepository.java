package com.darkanddarker.auction.repository.chat;

import com.darkanddarker.auction.model.chat.ChatMessage;
import com.darkanddarker.auction.model.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
    ChatMessage findFirstByChatRoomOrderByCreatedAtDesc(ChatRoom chatRoom);

}
