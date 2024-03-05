package am.smartcode.notification.controller;

import am.smartcode.notification.model.dto.SendEmailDto;
import am.smartcode.notification.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody SendEmailDto sendEmailDto) {
        emailService.sendEmail(sendEmailDto);
        return ResponseEntity.ok().build();
    }
}
