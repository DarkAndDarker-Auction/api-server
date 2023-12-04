package com.darkanddarker.auction.dto.notification;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SendNotificationRequestDto {
    private Long receiverId;
    private String title;
    private String body;
    private NotificationType notificationType;
    private Long auctionItemId;
    private Long chatRoomId;
}
