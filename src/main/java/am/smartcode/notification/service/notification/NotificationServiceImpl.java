package am.smartcode.notification.service.notification;

import am.smartcode.notification.mapper.NotificationMapper;
import am.smartcode.notification.model.dto.CreateNotificationDto;
import am.smartcode.notification.model.dto.NotificationDto;
import am.smartcode.notification.model.dto.SendEmailDto;
import am.smartcode.notification.model.entity.NotificationEntity;
import am.smartcode.notification.repository.NotificationRepository;
import am.smartcode.notification.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final EmailService emailService;


    @Override
    @Transactional
    public NotificationDto create(CreateNotificationDto createNotificationDto) {
        NotificationEntity notificationEntity = notificationMapper.toEntity(createNotificationDto);
        notificationEntity.setNotificationDateTime(createNotificationDto.getNotificationDate()
                .atZone(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli()
        );
        notificationEntity.setCreationDateTime(Instant.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());
        notificationEntity.setSent(false);
        notificationRepository.save(notificationEntity);

        return notificationMapper.toDto(notificationEntity);

    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getNotifications(boolean ready, Integer userId) {
        List<NotificationEntity> allBySentAndUserId = notificationRepository.findAllBySentAndUserId(ready, userId);
        return allBySentAndUserId.stream().map(notificationMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationEntity> getReadyNotifications() {
        return notificationRepository.getReadyNotofications(System.currentTimeMillis());
    }

    @Async("asyncExecutor")
    @Override
    @Transactional
    public void sendNotification(NotificationEntity readyNotification) throws InterruptedException {
        long waitTime = readyNotification.getNotificationDateTime() - System.currentTimeMillis();
        Thread.sleep(waitTime);

        SendEmailDto sendEmailDto = new SendEmailDto();
        sendEmailDto.setTo(readyNotification.getEmail());
        sendEmailDto.setSubject(readyNotification.getTitle());
        sendEmailDto.setText(readyNotification.getContent());

        emailService.sendEmail(sendEmailDto);
        readyNotification.setSent(true);
        notificationRepository.save(readyNotification);
    }


}
