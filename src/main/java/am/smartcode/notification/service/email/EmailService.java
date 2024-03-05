package am.smartcode.notification.service.email;

import am.smartcode.notification.model.dto.SendEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SendEmailDto sendEmailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmailDto.getTo());
        message.setSubject(sendEmailDto.getSubject());
        message.setText(sendEmailDto.getText());
        message.setFrom("testfortest891@gmail.com");
        javaMailSender.send(message);
    }

}
