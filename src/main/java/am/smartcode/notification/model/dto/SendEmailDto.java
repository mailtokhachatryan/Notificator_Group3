package am.smartcode.notification.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendEmailDto {
    private String to;
    private String subject;
    private String text;
}
