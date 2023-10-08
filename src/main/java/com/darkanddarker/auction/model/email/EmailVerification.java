package com.darkanddarker.auction.model.email;

import com.darkanddarker.auction.dto.EmailVerificationRequestDto;
import com.darkanddarker.auction.model.VerificationEventType;;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailVerification {

    @Id @GeneratedValue
    @Column(name = "email_verification_id")
    private Long id;
    private String email;
    private String code;
    private LocalDateTime expiredDate;

    public EmailVerification(String email) {
        this.email = email;
        this.expiredDate = LocalDateTime.now().plusMinutes(3);
    }

    public EmailVerification(String email, LocalDateTime expiredDate) {
        this.email = email;
        this.expiredDate = expiredDate;
    }

    public String generateCode() {
        final int CODE_LENGTH = 8;

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; i++) code.append((char) ((int) (random.nextInt(26)) + 65)); //  A~Z

        this.code = code.toString();
        return this.code;
    }

    public VerificationEventType verify(EmailVerificationRequestDto emailVerifyRequestDto) {
        if (!code.equals(emailVerifyRequestDto.getCode())) {
            return VerificationEventType.FAILURE;
        }
        if (!expiredDate.isAfter(LocalDateTime.now())) {
            return VerificationEventType.EXPIRED;
        }
        return VerificationEventType.SUCCESS;
    }
}
