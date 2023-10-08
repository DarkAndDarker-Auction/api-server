package com.darkanddarker.auction.common.utils.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailUtilsImpl implements EmailUtils {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("templates/emailVerificationTemplate.html")
    private String templateFileName;

    @Override
    public void sendVerificationCode(String email, String code) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender, "DnD Auction");
        helper.setTo(email);
        helper.setSubject("DnD Auction 회원 가입 인증 코드가 발송되었습니다.");

        String templateContent = readTemplateContent(templateFileName);
        templateContent = templateContent.replace("{{ePw}}", code);
        helper.setText(templateContent, true);
        javaMailSender.send(message);
    }

    private String readTemplateContent(String templateFileName) throws IOException {
        Resource resource = new ClassPathResource(templateFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
