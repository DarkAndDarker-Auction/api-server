package com.darkanddarker.auction.repository.notification;

import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.model.notification.NotificationHistory;
import com.darkanddarker.auction.model.notification.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {
    List<NotificationHistory> findByReceiverAndCheckedOrderByCreatedAtDesc(Member member, boolean checked);
    List<NotificationHistory> findByReceiverAndCheckedAndNotificationTypeOrderByCreatedAtDesc(Member member, boolean checked, NotificationType notificationType);
    List<NotificationHistory> findByReceiverOrderByCreatedAtDesc(Member member);
    List<NotificationHistory> findByReceiverAndNotificationTypeOrderByCreatedAtDesc(Member member, NotificationType notificationType);
}
