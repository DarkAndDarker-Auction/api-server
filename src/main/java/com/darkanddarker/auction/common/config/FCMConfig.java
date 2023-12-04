package com.darkanddarker.auction.common.config;

import com.darkanddarker.auction.common.exception.InternalServerError;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FCMConfig {

    @Value("${fcm.certification}")
    private String credential;

    @Bean
    FirebaseMessaging firebaseMessaging() {
        ClassPathResource resource = new ClassPathResource(credential);

        try (InputStream stream = resource.getInputStream()) {
            FirebaseApp firebaseApp = null;

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(stream))
                        .build();
                firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                for (FirebaseApp app : FirebaseApp.getApps()) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            }
            if (firebaseApp == null) {
                throw new InternalServerError("FCM 연결에 실패하였습니다.");
            }
            return FirebaseMessaging.getInstance(firebaseApp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerError("FCM 연결에 실패하였습니다.");
        }
    }
}