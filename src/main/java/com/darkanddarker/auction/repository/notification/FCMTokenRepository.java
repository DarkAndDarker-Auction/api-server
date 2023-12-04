package com.darkanddarker.auction.repository.notification;

import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.model.notification.FCMToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {
    boolean existsByMember(Member member);
    FCMToken findByMember(Member member);
}
