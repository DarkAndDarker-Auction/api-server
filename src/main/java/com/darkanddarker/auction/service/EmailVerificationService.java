package com.darkanddarker.auction.service;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.utils.email.EmailUtils;
import com.darkanddarker.auction.dto.auth.EmailVerificationRequestDto;
import com.darkanddarker.auction.dto.auth.SendVerificationCodeRequestDto;
import com.darkanddarker.auction.model.email.EmailVerification;
import com.darkanddarker.auction.model.email.EmailVerificationHistory;
import com.darkanddarker.auction.model.VerificationEventType;
import com.darkanddarker.auction.repository.EmailVerificationHistoryRepository;
import com.darkanddarker.auction.repository.EmailVerificationRepository;
import com.darkanddarker.auction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailVerificationService {

    private final MemberRepository memberRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailVerificationHistoryRepository emailVerificationHistoryRepository;
    private final EmailUtils emailUtils;

    public void verifyEmailDuplication(SendVerificationCodeRequestDto sendVerificationCodeRequestDto) {
        if (memberRepository.existsByEmail(sendVerificationCodeRequestDto.getEmail())) {
            throw new BadRequestException("중복되는 이메일이 존재합니다.");
        }
    }

    @Transactional
    public void sendVerificationCode(SendVerificationCodeRequestDto recipient) throws MessagingException, IOException {
        EmailVerification emailVerification = findOrCreateEmailVerification(recipient.getEmail());
        emailUtils.sendVerificationCode(recipient.getEmail(), emailVerification.generateCode());
        emailVerificationRepository.save(emailVerification);
    }

    @Transactional
    public void verify(EmailVerificationRequestDto verifyRequestDto) {
        EmailVerification emailVerification = emailVerificationRepository.findByEmail(verifyRequestDto.getEmail()).orElseThrow(
                () -> new BadRequestException("잘못된 입력입니다."));
        VerificationEventType verificationEventType = emailVerification.verify(verifyRequestDto);

        emailVerificationHistoryRepository.save(new EmailVerificationHistory(verificationEventType, emailVerification));
        verificationEventType.execute();
    }

    private EmailVerification findOrCreateEmailVerification(String email) {
        Optional<EmailVerification> emailVerification = emailVerificationRepository.findByEmail(email);
        if (emailVerification.isEmpty()) {
            emailVerification = Optional.of(new EmailVerification(email));
        }
        return emailVerification.get();
    }
}
