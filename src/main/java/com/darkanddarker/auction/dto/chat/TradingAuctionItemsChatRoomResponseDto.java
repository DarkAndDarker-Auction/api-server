package com.darkanddarker.auction.dto.chat;


import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.auction.TradeHistory;
import com.darkanddarker.auction.model.chat.ChatMessage;
import com.darkanddarker.auction.model.chat.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class TradingAuctionItemsChatRoomResponseDto {

    private ChatRoom chatRoom;
    private AuctionItem auctionItem;
    private TradeHistory tradeHistory;
    private ChatMessage latestChat;
}
