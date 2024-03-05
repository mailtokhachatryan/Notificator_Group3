package am.smartcode.notification.service.notification;

import am.smartcode.notification.model.dto.CreateNotificationDto;
import am.smartcode.notification.model.dto.NotificationDto;
import am.smartcode.notification.model.entity.NotificationEntity;

import java.util.List;

public interface NotificationService {
    NotificationDto create(CreateNotificationDto createNotificationDto);

    List<NotificationDto> getNotifications(boolean ready, Integer userId);

    List<NotificationEntity> getReadyNotifications();

    void sendNotification(NotificationEntity readyNotification) throws InterruptedException;
}
