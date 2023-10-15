package com.darkanddarker.auction.repository.email;

import com.darkanddarker.auction.model.email.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByEmail(String email);
    boolean existsByEmail(String email);

}
