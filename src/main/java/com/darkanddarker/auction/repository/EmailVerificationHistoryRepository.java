package com.darkanddarker.auction.repository;

import com.darkanddarker.auction.model.email.EmailVerificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationHistoryRepository extends JpaRepository<EmailVerificationHistory, Long> {
}
