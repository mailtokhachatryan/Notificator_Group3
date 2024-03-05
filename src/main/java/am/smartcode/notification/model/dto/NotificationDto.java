package am.smartcode.notification.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {

    private Integer id;

    private Integer userId;

    private String title;

    private  String content;

    private LocalDateTime notificationDate;

    private LocalDateTime creationDate;

    private Boolean sent;

    private String  email;
}
