package am.smartcode.notification.controller;

import am.smartcode.notification.model.dto.CreateNotificationDto;
import am.smartcode.notification.model.dto.NotificationDto;
import am.smartcode.notification.service.notification.NotificationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody @Valid CreateNotificationDto createNotificationDto) {
        return ResponseEntity.ok(notificationService.create(createNotificationDto));
    }

    @GetMapping("/ready")
    public ResponseEntity<List<NotificationDto>> getReady(@RequestParam @Positive Integer userId) {
        return ResponseEntity.ok(notificationService.getNotifications(true, userId));
    }

    @GetMapping("/waiting")
    public ResponseEntity<List<NotificationDto>> getWaiting(@RequestParam @Positive Integer userId) {
        return ResponseEntity.ok(notificationService.getNotifications(false, userId));
    }

}
