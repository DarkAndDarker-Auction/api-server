package com.darkanddarker.auction.controller;

import com.darkanddarker.auction.dto.notification.NotificationIdRequestDto;
import com.darkanddarker.auction.dto.notification.SendNotificationRequestDto;
import com.darkanddarker.auction.dto.notification.UpdateMemberFCMTokenRequestDto;
import com.darkanddarker.auction.model.notification.NotificationHistory;
import com.darkanddarker.auction.service.notification.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "알림 기능 API")
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendNotification(@RequestBody SendNotificationRequestDto sendNotificationRequestDto) {
        notificationService.sendNotification(sendNotificationRequestDto);
        return ResponseEntity.ok("알림 전송에 성공하였습니다.");
    }

    @PostMapping("/checked")
    public ResponseEntity<Object> notificationChecked(@RequestBody NotificationIdRequestDto notificationCheckedRequestDto) {
        notificationService.notificationChecked(notificationCheckedRequestDto);
        return ResponseEntity.ok("알림 확인에 성공하였습니다.");
    }

    @GetMapping("/unchecked")
    public ResponseEntity<List<NotificationHistory>> getUncheckedNotifications(@RequestHeader("Authorization") String authorizationHeader,
                                                                               @RequestParam String filter) {
        return ResponseEntity.ok(notificationService.getUncheckedNotifications(authorizationHeader, filter));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationHistory>> getAllNotifications(@RequestHeader("Authorization") String authorizationHeader,
                                                                         @RequestParam String filter) {
        return ResponseEntity.ok(notificationService.getAllNotifications(authorizationHeader, filter));
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteNotification(@RequestBody NotificationIdRequestDto notificationIdRequestDto) {
        notificationService.deleteNotification(notificationIdRequestDto);
        return ResponseEntity.ok("알림 삭제에 성공하였습니다.");
    }

    @PostMapping("/token/update")
    public ResponseEntity<Object> updateMemberFCMToken(@RequestBody UpdateMemberFCMTokenRequestDto updateMemberFCMTokenRequestDto,
                                                       @RequestHeader("Authorization") String authorizationHeader) {
        notificationService.updateMemberFCMToken(updateMemberFCMTokenRequestDto, authorizationHeader);
        return ResponseEntity.ok("FCM 토큰 업데이트 또는 등록에 성공하였습니다.");
    }

}
