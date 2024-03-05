package am.smartcode.notification.executor;

import am.smartcode.notification.model.entity.NotificationEntity;
import am.smartcode.notification.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationExecutor {

    private final NotificationService notificationService;

    //    @Scheduled(cron = "*/30 * * * * *")
    @Scheduled(fixedDelay = 30000, scheduler = "taskScheduler")
    public void start() throws InterruptedException {
        List<NotificationEntity> readyNotifications = notificationService.getReadyNotifications();
        for (NotificationEntity readyNotification : readyNotifications) {
            notificationService.sendNotification(readyNotification);
        }
    }

}
