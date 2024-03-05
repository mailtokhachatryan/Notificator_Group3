package am.smartcode.notification.repository;

import am.smartcode.notification.model.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

    List<NotificationEntity> findAllBySentAndUserId(Boolean sent, Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM notifications WHERE sent = false AND notification_date_time BETWEEN :time AND (:time + 30000)")
    List<NotificationEntity> getReadyNotofications(@Param("time") long currentMillis);

}