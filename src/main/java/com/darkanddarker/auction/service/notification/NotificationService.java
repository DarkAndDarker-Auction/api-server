package com.darkanddarker.auction.service.notification;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.exception.InternalServerError;
import com.darkanddarker.auction.common.exception.NotFoundException;
import com.darkanddarker.auction.common.utils.auth.AuthUtils;
import com.darkanddarker.auction.dto.notification.NotificationIdRequestDto;
import com.darkanddarker.auction.dto.notification.SendNotificationRequestDto;
import com.darkanddarker.auction.dto.notification.UpdateMemberFCMTokenRequestDto;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.model.notification.FCMToken;
import com.darkanddarker.auction.model.notification.NotificationHistory;
import com.darkanddarker.auction.model.notification.NotificationType;
import com.darkanddarker.auction.repository.auction.AuctionItemRepository;
import com.darkanddarker.auction.repository.member.MemberRepository;
import com.darkanddarker.auction.repository.notification.FCMTokenRepository;
import com.darkanddarker.auction.repository.notification.NotificationHistoryRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final AuthUtils authUtils;
    private final NotificationHistoryRepository notificationHistoryRepository;
    private final MemberRepository memberRepository;
    private final FCMTokenRepository fcmTokenRepository;
    private final AuctionItemRepository auctionItemRepository;

    @Transactional
    public void notificationChecked(NotificationIdRequestDto notificationIdRequestDto) {
        NotificationHistory notificationHistory = notificationHistoryRepository.findById(notificationIdRequestDto.getNotificationId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 알림 요청입니다."));

        if (notificationHistory.getChecked()) {
            throw new BadRequestException("이미 확인한 알림입니다.");
        }

        notificationHistory.notificationChecked();
        notificationHistoryRepository.save(notificationHistory);
    }

    public List<NotificationHistory> getUncheckedNotifications(String authorizationHeader, String filter) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        if (Objects.equals(filter, "ALL")) {
            return notificationHistoryRepository.findByReceiverAndCheckedOrderByCreatedAtDesc(member, false);
        }
        NotificationType notificationType = NotificationType.valueOf(filter);
        return notificationHistoryRepository.findByReceiverAndCheckedAndNotificationTypeOrderByCreatedAtDesc(member, false, notificationType);
    }

    public List<NotificationHistory> getAllNotifications(String authorizationHeader, String filter) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        if (Objects.equals(filter, "ALL")) {
            return notificationHistoryRepository.findByReceiverOrderByCreatedAtDesc(member);
        }
        NotificationType notificationType = NotificationType.valueOf(filter);
        return notificationHistoryRepository.findByReceiverAndNotificationTypeOrderByCreatedAtDesc(member, notificationType);
    }

    @Transactional
    public void sendNotification(SendNotificationRequestDto sendNotificationRequestDto) {
        Member receiver = memberRepository.findById(sendNotificationRequestDto.getReceiverId())
                .orElseThrow(() -> new NotFoundException("잘못된 사용자 정보입니다. 찾을 수 없음 memberId : " + sendNotificationRequestDto.getReceiverId()));
        AuctionItem auctionItem = auctionItemRepository.findById(sendNotificationRequestDto.getAuctionItemId())
                .orElseThrow(() -> new NotFoundException("잘못된 물품 정보입니다. 찾을 수 없음 auctionItemId : " + sendNotificationRequestDto.getAuctionItemId()));
        FCMToken fcmToken = fcmTokenRepository.findByMember(receiver);

        if (fcmToken == null) {
            throw new BadRequestException("해당 유저의 FCM 토큰정보가 없습니다. (수신자 기기 알림 권한 허용시 가능)");
        }

        Notification notification = Notification.builder()
                .setTitle(sendNotificationRequestDto.getTitle())
                .setBody(sendNotificationRequestDto.getBody())
                .build();
        Message message = Message.builder()
                .setToken(fcmToken.getToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new InternalServerError("알림 전송에 실패하였습니다.");
        }

        // 새로운 메시지는 알림 히스토리에 저장하지 않음.
        if(sendNotificationRequestDto.getNotificationType().equals(NotificationType.NEW_MESSAGE)) {
            return;
        }

        notificationHistoryRepository.save(NotificationHistory.builder()
                        .notificationType(sendNotificationRequestDto.getNotificationType())
                        .auctionItem(auctionItem)
                        .title(sendNotificationRequestDto.getTitle())
                        .body(sendNotificationRequestDto.getBody())
                        .receiver(receiver)
                .build());
    }

    @Transactional
    public void deleteNotification(NotificationIdRequestDto notificationIdRequestDto) {
        NotificationHistory notificationHistory = notificationHistoryRepository.findById(notificationIdRequestDto.getNotificationId())
                .orElseThrow(() -> new NotFoundException("잘못된 알림 히스토리입니다. NotificationId : " + notificationIdRequestDto.getNotificationId()));
        notificationHistoryRepository.delete(notificationHistory);
    }

    @Transactional
    public void updateMemberFCMToken(UpdateMemberFCMTokenRequestDto updateMemberFCMTokenRequest, String authorizationHeader) {
        Member member = authUtils.extractMemberFromToken(authorizationHeader);
        FCMToken fcmToken = fcmTokenRepository.findByMember(member);

        if (fcmToken == null) {
            fcmTokenRepository.save(FCMToken.builder()
                    .member(member)
                    .token(updateMemberFCMTokenRequest.getToken())
                    .build());
            return;
        }
        fcmToken.updateToken(updateMemberFCMTokenRequest.getToken());
        fcmTokenRepository.save(fcmToken);
    }
}
