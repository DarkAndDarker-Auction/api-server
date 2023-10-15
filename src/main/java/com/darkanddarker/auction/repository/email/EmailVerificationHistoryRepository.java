package com.darkanddarker.auction.repository.email;

import com.darkanddarker.auction.model.email.EmailVerificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationHistoryRepository extends JpaRepository<EmailVerificationHistory, Long> {
}
